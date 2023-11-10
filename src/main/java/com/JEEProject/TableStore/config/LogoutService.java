package com.JEEProject.TableStore.config;

import com.JEEProject.TableStore.Auth.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;
    private final HttpServletRequest HttpRequest;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        HttpSession session = HttpRequest.getSession();
        final String tokenSession = (String) session.getAttribute("accessToken");
        if ((authHeader == null ||!authHeader.startsWith("Bearer ")) && (tokenSession==null)) {
            return;
        }
        if(authHeader!=null){
            jwt = authHeader.substring(7);
        }else {
            jwt=tokenSession;
            session.removeAttribute("accessToken");
        }
        var storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
}