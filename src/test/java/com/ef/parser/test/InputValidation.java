package com.ef.parser.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.ef.parser.utils.Duration;
import com.ef.parser.utils.Utils;

@RunWith(SpringRunner.class)
public class InputValidation {

	@Test
	public void testInput() {
		
		// Check the duration
		assertThat("daily").isEqualTo(Duration.DAILY.getDuration());
		assertThat("hourly").isEqualTo(Duration.HOURLY.getDuration());
		
		// Check the start date format and sql date format
		assertThat("yyyy-MM-dd.HH:mm:ss").isEqualTo(Utils.DATE_FORMAT_INPUT);
		assertThat("yyyy-MM-dd HH:mm:ss").isEqualTo(Utils.DATE_FORMAT_SQL);
		
	}

}
