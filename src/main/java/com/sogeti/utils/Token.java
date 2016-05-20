package com.sogeti.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

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
	public static String generateToken(final int idMembre, final int idClient) {
		
		// on instancie le JWTBuilder
		final JwtBuilder lJwtBuilder = Jwts.builder();
		
		// 1 - Partie Header
		lJwtBuilder.setHeaderParam("alg", "HS256");
		lJwtBuilder.setHeaderParam("typ", "JWT");
		
		// 2 - Payload
		// Claims
		final Claims lClaims = Jwts.claims();
		// id membre et idClient
		lClaims.put("id_membre", idMembre);
		lClaims.put("idClient", idClient);
		// Sujet (subject)
		lClaims.put("subject", "totot");
		// Date de création du token
		lClaims.put("iat", new Date().getTime());
		// Date d’expiration du token (expiration)
		final Calendar lCal = Calendar.getInstance();
		lCal.setTime(new Date());		
		long timeStamp = lCal.getTime().getTime();		
		lClaims.put("expiration", timeStamp + 60);

		lJwtBuilder.setClaims(lClaims);

		// 3 - Signature 
		// TODO définir une clé (exemle : SOGETIpIC3456698)
		lJwtBuilder.signWith(SignatureAlgorithm.HS256, "SOGETIpIC3456698");
		 
		// La méthode compact réalise l'encodage en Base64URL et concaténer les 
		// 3 parties du JWT (Header + Payload + Signature)
		return lJwtBuilder.compact();

	}
	
	/**
	 * Cette méthode permet de découper le token JWT et retourner seulement
	 * la partie Payload
	 * @param jwt
	 */
	private static void decouperJwt (String jwt)
	{
		// TODO à faire et savoir les données qui nous intéressent
		if (Jwts.parser().isSigned(jwt))
		{
			Claims claims = Jwts.parser().setSigningKey("SOGETIpIC3456698").parseClaimsJws(jwt).getBody();
			
			claims.get("id_membre");
			claims.get("id_mebmre");
			claims.get("subject");
			claims.get("iat");
			claims.get("expiration");
		}
	}
	
	/**
	 * Cette méthode permet d'obtenir l'id client via le token
	 * @param jwt le token
	 * @return l'id client
	 */
	public static int obtenirIdClient(final String jwt)
	{
		int lIdClient = 0;
		
		// on vérifie si le token est signé
		if (Jwts.parser().isSigned(jwt))
		{
			// on obtient le claims (Payload)
			final Claims lClaims = Jwts.parser().setSigningKey("SOGETIpIC3456698").parseClaimsJws(jwt).getBody();
			
			if (lClaims.containsKey("idClient")) {
				lIdClient = Integer.parseInt(lClaims.get("idClient").toString());
			}
		}
		return lIdClient;
	}
	
	/**
	 * 
	 * @param jwt
	 */
	/*	public static Long getTimestamp (String jwt)
	{
		// TODO à faire et savoir les données qui nous intéressent
		Long timeStamp = Long.MAX_VALUE;
		if (Jwts.parser().isSigned(jwt))
		{
			Claims claims = Jwts.parser().setSigningKey("SOGETIpIC3456698").parseClaimsJws(jwt).getBody();
			
			if(claims != null && claims.containsKey("expiration")  && claims.get("expiration") != null) {

				 timeStamp  = Long.parseLong(claims.get("expiration").toString());
				
			}
		}
		
		return timeStamp;
		
	}*/
	
}
