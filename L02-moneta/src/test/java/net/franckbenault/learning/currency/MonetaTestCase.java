package net.franckbenault.learning.currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.money.CurrencyQueryBuilder;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.UnknownCurrencyException;

import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MonetaTestCase {
	
	@Nested
	@DisplayName("Tests for currency unit")
	class CurrencyUnitTestCase {

		@Test
		public void testCurrencyDollarByAlphaAndNumericCode() {
		    CurrencyUnit usd = Monetary.getCurrency("USD");
		    CurrencyUnit usd2 = Monetary.getCurrency(
		    		CurrencyQueryBuilder.of().setNumericCodes(840).build()
		    		);
		
		    assertNotNull(usd);
		    assertEquals(usd.getCurrencyCode(), "USD");
		    assertEquals(usd.getNumericCode(), 840);
		    assertEquals(usd.getDefaultFractionDigits(), 2);
		    System.out.println(usd);
		
		    assertNotNull(usd2);
		    assertEquals(usd2.getCurrencyCode(), "USD");
		    assertEquals(usd2.getNumericCode(), 840);
		    assertEquals(usd2.getDefaultFractionDigits(), 2);
		    System.out.println(usd2);
		    
		}

		@Test
		public void testCurrencyEuroByAlphaAndNumericCode() {
		    CurrencyUnit euro = Monetary.getCurrency("EUR");
		    CurrencyUnit euro2 = Monetary.getCurrency(
		    		CurrencyQueryBuilder.of().setNumericCodes(978).build()
		    		);
		
		    assertNotNull(euro);
		    assertEquals(euro.getCurrencyCode(), "EUR");
		    assertEquals(euro.getNumericCode(), 978);
		    assertEquals(euro.getDefaultFractionDigits(), 2);
		    System.out.println(euro);
		    
		    assertNotNull(euro2);
		    assertEquals(euro2.getCurrencyCode(), "EUR");
		    assertEquals(euro2.getNumericCode(), 978);
		    assertEquals(euro2.getDefaultFractionDigits(), 2);
		    System.out.println(euro2);
		}

		@Test
		public void givenCurrencyCode_whenNoExist_thanThrowsError() {
			
			 assertThrows(UnknownCurrencyException.class,() -> Monetary.getCurrency("AAA")); 
		    
		}
		
	}
	
	@Nested
	@DisplayName("Tests for amount")
	class AmountTestCase {

		@Test
		public void testAmountDivide() {
		    MonetaryAmount threeDollar = Monetary.getDefaultAmountFactory()
		      .setCurrency("USD").setNumber(3).create();
		    MonetaryAmount oneDollar = threeDollar.divide(3);
		    System.out.println(oneDollar);
		    assertEquals(oneDollar, Money.of(1, "USD"));
		}

		@Test
		public void testAmountEquals() {
		    MonetaryAmount oneDolar = Monetary.getDefaultAmountFactory()
		      .setCurrency("USD").setNumber(1).create();
		    Money oneEuro = Money.of(1, "EUR");
		
		    assertTrue(oneEuro.equals(Money.of(1, "EUR")));
		    assertTrue(oneDolar.equals(Money.of(1, "USD")));
		}

		@Test
		public void testAmounts() {
		
		    CurrencyUnit usd = Monetary.getCurrency("USD");
		    
		    //interface
		    MonetaryAmount fstAmtUSD = Monetary.getDefaultAmountFactory()
		      .setCurrency(usd).setNumber(200).create();
		    
		    //use long
		    Money moneyof = Money.of(12, usd);
		    //use decimal -> faster
		    FastMoney fastmoneyof = FastMoney.of(2, usd);
		
		    assertEquals("USD 200.00", fstAmtUSD.toString());
		    assertEquals("USD 12.00", moneyof.toString());
		    assertEquals("USD 2.00", fastmoneyof.toString());
		}
		
	}

}
