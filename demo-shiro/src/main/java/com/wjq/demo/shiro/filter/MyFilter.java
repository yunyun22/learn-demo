package com.wjq.demo.shiro.filter;

import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.filter.PathConfigProcessor;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author wjq
 * @since 2021-10-11
 */
public class MyFilter extends OncePerRequestFilter implements PathConfigProcessor {


    protected Map<String, List<Map.Entry<String, String>>> appliedPath = new LinkedHashMap<>();


    protected PatternMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("this is my filter");

        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String pathWithinApplication = WebUtils.getPathWithinApplication(httpServletRequest);
        for (String path : this.appliedPath.keySet()) {
            if (pathMatcher.matches(path, pathWithinApplication)) {
                List<Map.Entry<String, String>> list = appliedPath.get(path);

                for (Map.Entry<String, String> e : list) {
                    if (!Objects.equals(httpServletRequest.getHeader(e.getKey()), e.getValue())) {
                        System.out.println("error,not exist " + e.getKey() + ",dont visit this site");
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }


    @Override
    public Filter processPathConfig(String path, String config) {
        String[] split = config.split(",");
        List<Map.Entry<String, String>> list = new ArrayList<>();
        for (String s : split) {
            String[] split1 = s.split("=");
            String key = split1[0];
            String value = split1[1];
            list.add(new MyEntry<>(key, value));
        }
        appliedPath.put(path, list);
        return this;
    }

    private static class MyEntry<K, V> implements Map.Entry<K, V> {
        public MyEntry(K k, V v) {
            this.k = k;
            this.v = v;
        }

        private final K k;
        private V v;

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V value) {
            return this.v = value;
        }
    }
}
