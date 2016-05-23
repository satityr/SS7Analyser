package com.inov.ss7analyser.protocoles;


import org.jnetpcap.packet.JRegistry;
import org.jnetpcap.packet.RegistryHeaderErrors;


/**
 *
 * @author Abderrahim OUBIDAR
 */

public class RegisterNewProtocole {

	public void RegisterProtocoles(){       
		try {
		        JRegistry.register(com.inov.ss7analyser.protocoles.M3ua.class);
		        JRegistry.register(com.inov.ss7analyser.protocoles.M3uaNetworkAp.class);
		        JRegistry.register(com.inov.ss7analyser.protocoles.M3uaData.class);
		    } catch (RegistryHeaderErrors ex) {
		       
		    } 
		}
	
}
