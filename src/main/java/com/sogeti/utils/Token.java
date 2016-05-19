package com.sogeti.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.IdClass;

public class Token {

	/**
	 * Cette méthode genere un token JSON Web Token (JWT)
	 * En general, le JWT contient 3 parties : Header, Payload, Signature
	 * 
	 */
	public static String generateToken(int idMembre, int idClient)
	{
		
		
		JwtBuilder jwtBuilder = Jwts.builder();
		
		// 1 - Partie Header
		jwtBuilder.setHeaderParam("alg", "HS256");
		jwtBuilder.setHeaderParam("typ", "JWT");
		
		
		// TODO savoir les informations nécessaires pour les mettre dans le Claims
		// 2 - Payload
		// Claims
		Claims claims = Jwts.claims();
		// id membre et idClient
		claims.put("id_mebmre", idMembre);
		claims.put("idClient", idClient);
		// Sujet (subject)
		claims.put("subject", "totot");
		// Date de création du token
		claims.put("iat", new Date().getTime());
		// Date d’expiration du token (expiration)
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		long timeStamp = cal.getTime().getTime();
		
		claims.put("expiration", timeStamp);

		jwtBuilder.setClaims(claims);

		// 3 - Signature 
		// TODO définir une clé (exemle : SOGETIpIC3456698)
		jwtBuilder.signWith(SignatureAlgorithm.HS256, "SOGETIpIC3456698");
		 
		// La méthode compact réalise l'encodage en Base64URL et concaténer les 
		// 3 parties du JWT (Header + Payload + Signature)
		return jwtBuilder.compact();

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
			
			claims.get("id_mebmre");
			claims.get("id_mebmre");
			claims.get("subject");
			claims.get("iat");
			claims.get("expiration");
		}
	}
	public static int obtenirIdClient(String jwt)
	{
		int idClient = 0;
		// TODO à faire et savoir les données qui nous intéressent
				if (Jwts.parser().isSigned(jwt))
				{
					Claims claims = Jwts.parser().setSigningKey("SOGETIpIC3456698").parseClaimsJws(jwt).getBody();
					
					claims.get("idClient");
				}
				return idClient;
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
