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

	private static final String IDMEMBRE = "idMembre";
	private static final String IDCLIENT = "idClient";
	private static final String SUBJECT = "subject";
	private static final String IAT = "iat";
	private static final String EXPRIRATION = "expiration";
	private static final String CLE_SIGNATURE_TOKEN = "SOGETIpIC3456698";
	private static final String AUTHORIZATION = "Authorization";
	private static final String ALG = "alg";
	private static final String TYP = "typ";
	private static final String JWT = "jwt";
	
	/**
	 * Cette méthode genere un token JSON Web Token (JWT)
	 * En general, le JWT contient 3 parties : Header, Payload, Signature
	 * 
	 */
	public static String generateToken(final int pIdMembre, final int pIdClient) {
		
		// on instancie le JWTBuilder
		final JwtBuilder jwtBuilder = Jwts.builder();
		
		// 1 - Partie Header
		jwtBuilder.setHeaderParam(ALG, SignatureAlgorithm.HS256);
		jwtBuilder.setHeaderParam(TYP, JWT);
		
		// 2 - Payload
		// Claims
		final Claims claims = Jwts.claims();
		// id membre et idClient
		claims.put(IDMEMBRE, pIdMembre);
		claims.put(IDCLIENT, pIdClient);
		// Sujet (subject)
		claims.put(SUBJECT, "Application PIC");
		// Date de création du token
		claims.put(IAT, new Date().getTime());
		// Date d’expiration du token (expiration)
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());		
		long timeStamp = cal.getTime().getTime();		
		claims.put(EXPRIRATION, timeStamp + 60);

		jwtBuilder.setClaims(claims);
		
		// 3 - Signature 
		// TODO définir une clé (exemle : SOGETIpIC3456698)
		jwtBuilder.signWith(SignatureAlgorithm.HS256, CLE_SIGNATURE_TOKEN);
		 
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
			final Claims claims = Jwts.parser().setSigningKey(CLE_SIGNATURE_TOKEN).parseClaimsJws(pJwt).getBody();
			
			if (claims.containsKey(IDCLIENT)) {
				idClient = Integer.parseInt(claims.get(IDCLIENT).toString());
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
			final Claims claims = Jwts.parser().setSigningKey(CLE_SIGNATURE_TOKEN).parseClaimsJws(pJwt).getBody();
			
			if (claims.containsKey(IDMEMBRE)) {
				idMembre = Integer.parseInt(claims.get(IDMEMBRE).toString());
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
		
		if (pHeaders.get(AUTHORIZATION) != null && StringUtils.isNotBlank(pHeaders.get(AUTHORIZATION).toString())) {
			final String authorization = pHeaders.get(AUTHORIZATION).toString();
			final int endIndex = authorization.length() - 3;
			token = authorization.substring(beginIndex, endIndex);
		}
		
		return token;
	}

}
