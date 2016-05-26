package com.sogeti.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;

/**
 * 
 * @author moissa
 *
 */

public class Token {

	
	/**
	 * Cette méthode genere un token JSON Web Token (JWT)
	 * En general, le JWT contient 3 parties : Header, Payload, Signature
	 * 
	 */
	public static String generateToken(final int pIdMembre, final int pIdClient) {
		
		// on instancie le JWTBuilder
		final JwtBuilder jwtBuilder = Jwts.builder();
		
		// 1 - Partie Header
		jwtBuilder.setHeaderParam("alg", "HS256");
		jwtBuilder.setHeaderParam("typ", "JWT");
		
		// 2 - Payload
		// Claims
		final Claims claims = Jwts.claims();
		// id membre et idClient
		claims.put("idMembre", pIdMembre);
		claims.put("idClient", pIdClient);
		// Sujet (subject)
		claims.put("subject", "totot");
		// Date de création du token
		claims.put("iat", new Date().getTime());
		// Date d’expiration du token (expiration)
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());		
		long timeStamp = cal.getTime().getTime();		
		claims.put("expiration", timeStamp + 60);

		jwtBuilder.setClaims(claims);
		
		// 3 - Signature 
		// TODO définir une clé (exemle : SOGETIpIC3456698)
		jwtBuilder.signWith(SignatureAlgorithm.HS256, "SOGETIpIC3456698");
		 
		// La méthode compact réalise l'encodage en Base64URL et concaténer les 
		// 3 parties du JWT (Header + Payload + Signature)
		return jwtBuilder.compact();

	}
	
	/**
	 * Cette méthode permet d'obtenir l'id client via le token
	 * @param pJwt le token
	 * @return l'id client
	 */
	public static int obtenirIdClient(final String pJwt) {
		
		int idClient = 0;
		
		// on vérifie si le token est signé
		if (StringUtils.isNotBlank(pJwt) && Jwts.parser().isSigned(pJwt)) {
			// on obtient le claims (Payload)
			final Claims claims = Jwts.parser().setSigningKey("SOGETIpIC3456698").parseClaimsJws(pJwt).getBody();
			
			if (claims.containsKey("idClient")) {
				idClient = Integer.parseInt(claims.get("idClient").toString());
			}
		}
		return idClient;
	}
	
	/**
	 * Cette méthode permet d'obtenir l'id membre via le token
	 * @param pJwt le token
	 * @return l'id membre
	 */
	public static int obtenirIdMembre(final String pJwt) {
		
		int idMembre = 0;
		
		// on vérifie si le token est signé
		if (StringUtils.isNotBlank(pJwt) && Jwts.parser().isSigned(pJwt)) {
			// on obtient le claims (Payload)
			final Claims claims = Jwts.parser().setSigningKey("SOGETIpIC3456698").parseClaimsJws(pJwt).getBody();
			
			if (claims.containsKey("idMembre")) {
				idMembre = Integer.parseInt(claims.get("idMembre").toString());
			}
		}
		return idMembre;
	}
	
	/**
	 * Cette méthode permet d'obtenir l'id membre via le token
	 * @param pHeaders le http headers
	 * @return le token
	 */
	public static String obtenirTokenByhttpHeaders(final HttpHeaders pHeaders) {
		
		// on récupère le token via le header
		String token = "";
		final int beginIndex = 26;
		
		if (pHeaders.get("Authorization") != null && StringUtils.isNotBlank(pHeaders.get("Authorization").toString())) {
			final String authorization = pHeaders.get("Authorization").toString();
			final int endIndex = authorization.length() - 3;
			token = authorization.substring(beginIndex, endIndex);
		}
		
		return token;
	}

}
