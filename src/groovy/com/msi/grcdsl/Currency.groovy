package com.msi.grcdsl

import groovy.transform.TupleConstructor;

@TupleConstructor
class Currency {

	double amount
	CurrencyUnit currencyUnit

	String toString() {
		"$amount $currencyUnit"
	}

	@Override
	public boolean equals(Object obj) {

		if(obj instanceof Currency) {
			Currency that = (Currency) obj;
			if (that.getValue() == this.getValue())
				return true
		}
		return false
	}

	public double getValue() {

		return (amount / currencyUnit.multiplier)
	}
}
