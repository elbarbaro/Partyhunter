package com.daniel.partyhunter;

import java.util.UUID;

public class Hermes {
	private String cerberusAddres = "http://partyhunter.mx/";
	private String file;
	private static UUID godID;
	 
	public String talkToOlimpus(String file, String peticiones) throws Exception{
		this.setFile(file);
		Hermes.godID = UUID.randomUUID();
		MessageEnvelope msg = new MessageEnvelope(this.cerberusAddres+this.file);
		String CerberusAnswer =  msg.sendVars(peticiones + "&godID=" + Hermes.godID);
		System.out.println(CerberusAnswer);
		return CerberusAnswer;
	}
	 
	public void setFile(String file){this.file = file;}
	
}
