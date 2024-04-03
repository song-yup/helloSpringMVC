package kr.ac.hansung.cse.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")    // root 이하의 모든 것을 가로챈다
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println( ((HttpServletRequest) request).getRequestURL() );   // 여기서 사용자의 requesturl을 넣어줬다, log에 url이 찍히는 이유는 이 문장 때문이다

        // pass the request along the filter chain  (그 다음 filter chain으로 넘어간다)
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
