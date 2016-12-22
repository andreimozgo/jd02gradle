package by.academy.it.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" }, initParams = {
        @WebInitParam (name = "encoding", value = "UTF-8", description = "Encoding Param") })

public class EncodingFilter implements Filter {
    final Logger LOG = Logger.getLogger(EncodingFilter.class);
    private String code;

    public void init(FilterConfig fConfig) throws ServletException {
        code = fConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        // set encoding if don't exist
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
            LOG.info("Encoding changed to UTF-8 successfully");
        }

        chain.doFilter(request, response); }

    public void destroy() {
        code = null;
    }
}