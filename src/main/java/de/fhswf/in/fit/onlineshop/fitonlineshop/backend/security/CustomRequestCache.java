package de.fhswf.in.fit.onlineshop.fitonlineshop.backend.security;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomRequestCache extends HttpSessionRequestCache {

    /**
     * Speichert nicht authentifizierte Anforderungen, damit der Benutzer
     * auf die Seite umgeleitet werden kann, auf die er zugreifen wollte,
     * sobald er angemeldet ist
     *
     * @param request   Anfrage
     * @param response  Antwort
     */

    @Override
    public void saveRequest(HttpServletRequest request, HttpServletResponse response){

        if(!SecurityUtils.isFrameworkInternalRequest(request)){
            super.saveRequest(request, response);
        }
    }
}
