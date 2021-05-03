package ca.uqtr.dmi.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//https://youtu.be/her_7pa0vrg?t=15339
public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse rep,
                                    FilterChain filterChain) throws ServletException, IOException {
        String auth = req.getHeader("Authorization");
        if (Strings.isNullOrEmpty(auth) || !auth.startsWith("Bearer")) {
             /*
          若在这种情况下，token验证失败，把请求发到下一个filter或下一个责任链中如security configue中的
           .authorizeRequests()
           .antMatchers("/login", "/api/users/signup")
           .permitAll()
            .anyRequest().authenticated();//其他的请求都要要求验证！！！

            所以在我们这个程序其他类型请求若是验证token失败就真的失败了！
           */
            filterChain.doFilter(req, rep);
        } else {
            String token = auth.replace("Bearer ", "");
            // voir https://github.com/auth0/java-jwt
            try {
                Algorithm algorithm = Algorithm.HMAC256("123456789 wwwwwwwwwweirieieee");
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer("MyApp")
                        .build();
                DecodedJWT jwt = verifier.verify(token);
                String username = jwt.getSubject();
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(username, null, null);


                SecurityContextHolder.getContext().setAuthentication(authentication);
                //这一行很重要，是SecurityContextHolder来验证该请求是否具有权限，若没有，且它并不是login或signuo则请求失败！！

                filterChain.doFilter(req, rep);////验证完成后进入下一个责任链，同if中的解析！！
            } catch (JWTVerificationException exception) {
                exception.printStackTrace();
                //rep.sendError(HttpServletResponse.SC_FORBIDDEN);
                throw new RuntimeException(exception.getMessage());
            }
        }

    }
}
