/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author truon
 */
public class baitap

{  
  public float getDisc(int years){
      return (years > 5) ? (float)5/100 : (float)years/100;
  }
  public double type1(float amount){
      return amount;
  }
    public double type2(float amount,float disc){
      return (amount - (0.1 * amount)) - disc * (amount - (0.1 * amount));
  }
    public double type3(float amount,float disc){
      return (0.7 * amount) - disc * (0.7 * amount);
  }
    public double type4(float amount,float disc){
      return (amount - (0.5 * amount)) - disc * (amount - (0.5 * amount));
  }
  public double Calculate(float amount, int type, int years)

  {
    float disc = getDisc(years);
    if (type == 1){
      return type1(amount);
    } else if (type == 2){
      return type2(amount, disc);
    } else if (type == 3){
      return type3(amount, disc);
    } else if (type == 4){
      return type4(amount, disc);
    }
    return 0;
  }
}