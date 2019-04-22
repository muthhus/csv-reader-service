package com.telekomatrix.csv.reader.impl;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerInfo {
	private String firstName;
	private String middleName;
	private String surname;
	private int age;
	private String city;
	private int year;

}
