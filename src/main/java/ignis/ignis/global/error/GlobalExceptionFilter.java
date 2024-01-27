package ignis.ignis.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import ignis.ignis.global.error.exception.BusinessException;
import ignis.ignis.global.error.exception.ErrorCode;
import ignis.ignis.global.error.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e){
            ErrorCode errorCode = e.getErrorCode();
            writeErrorResponse(response, errorCode.getStatus(), ErrorResponse.of(errorCode, errorCode.getMessage()));
        } catch (Exception e){
            writeErrorResponse(response, response.getStatus(), ErrorResponse.of(response.getStatus(), e.getMessage()));
        }
    }

    private void writeErrorResponse(HttpServletResponse response, int statusCode, ErrorResponse errorResponse) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }
}
