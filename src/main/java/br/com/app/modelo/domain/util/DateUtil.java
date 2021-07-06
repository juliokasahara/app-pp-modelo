package br.com.app.modelo.domain.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	
	public String formatarDataPtBR(LocalDateTime localDateTime, boolean comMinutos) {
		if(comMinutos)
			return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(localDateTime);
		else
			return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDateTime);
	}

}
