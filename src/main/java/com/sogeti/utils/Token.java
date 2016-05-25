package com.sogeti.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;

/**
 * 
 * @author moissa
 *
 */

public class Token {

	@Value("${com.sogeti.cleToken}")
	private static String cleSignature;
	
	
	/**
	 * Cette m�thode genere un token JSON Web Token (JWT)
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
		// Date de cr�ation du token
		claims.put("iat", new Date().getTime());
		// Date d�expiration du token (expiration)
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());		
		long timeStamp = cal.getTime().getTime();		
		claims.put("expiration", timeStamp + 60);

		jwtBuilder.setClaims(claims);
		
		System.out.println("Cl� Signature = " + cleSignature);
		
		// 3 - Signature 
		// TODO d�finir une cl� (exemle : SOGETIpIC3456698)
		jwtBuilder.signWith(SignatureAlgorithm.HS256, "SOGETIpIC3456698");
		 
		// La m�thode compact r�alise l'encodage en Base64URL et concat�ner les 
		// 3 parties du JWT (Header + Payload + Signature)
		return jwtBuilder.compact();

	}
	
	/**
	 * Cette m�thode permet de d�couper le token JWT et retourner seulement
	 * la partie Payload
	 * @param jwt
	 */
	private static void decouperJwt (String jwt)
	{
		// TODO � faire et savoir les donn�es qui nous int�ressent
		if (Jwts.parser().isSigned(jwt))
		{
			Claims claims = Jwts.parser().setSigningKey("SOGETIpIC3456698").parseClaimsJws(jwt).getBody();
			
			claims.get("id_membre");
			claims.get("id_membre");
			claims.get("subject");
			claims.get("iat");
			claims.get("expiration");
		}
	}
	
	/**
	 * Cette m�thode permet d'obtenir l'id client via le token
	 * @param pJwt le token
	 * @return l'id client
	 */
	public static int obtenirIdClient(final String pJwt)
	{
		int idClient = 0;
		
		// on v�rifie si le token est sign�
		if (StringUtils.isNotBlank(pJwt) && Jwts.parser().isSigned(pJwt))
		{
			// on obtient le claims (Payload)
			final Claims claims = Jwts.parser().setSigningKey("SOGETIpIC3456698").parseClaimsJws(pJwt).getBody();
			
			if (claims.containsKey("idClient")) {
				idClient = Integer.parseInt(claims.get("idClient").toString());
			}
		}
		return idClient;
	}
	
	/**
	 * Cette m�thode permet d'obtenir l'id membre via le token
	 * @param pJwt le token
	 * @return l'id membre
	 */
	public static int obtenirIdMembre(final String pJwt)
	{
		int idMembre = 0;
		
		// on v�rifie si le token est sign�
		if (StringUtils.isNotBlank(pJwt) && Jwts.parser().isSigned(pJwt))
		{
			// on obtient le claims (Payload)
			final Claims claims = Jwts.parser().setSigningKey("SOGETIpIC3456698").parseClaimsJws(pJwt).getBody();
			
			if (claims.containsKey("idMembre")) {
				idMembre = Integer.parseInt(claims.get("idMembre").toString());
			}
		}
		return idMembre;
	}
	
	/**
	 * Cette m�thode permet d'obtenir l'id membre via le token
	 * @param pJwt le token
	 * @return l'id membre
	 */
	public static String obtenirTokenByhttpHeaders(final HttpHeaders pHeaders)
	{
		// on r�cup�re le token via le header
		String token = "";
		final String authorization = pHeaders.get("Authorization").toString();
		if (StringUtils.isNotBlank(authorization))
		{
			token = authorization.substring(26, authorization.length() - 3);
		}
		
		return token;
	}
	
	/**
	 * 
	 * @param jwt
	 */
	/*	public static Long getTimestamp (String jwt)
	{
		// TODO � faire et savoir les donn�es qui nous int�ressent
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
