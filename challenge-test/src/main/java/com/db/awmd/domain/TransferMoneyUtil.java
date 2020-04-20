package com.db.awmd.domain;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransferMoneyUtil {
	@NotNull
	private String accountFromId;
	@NotNull
	private String accountToId;
	@NotNull
	private BigDecimal transferAmmount;

}
