package src;

public class Spielbrett {

  private final SpielStein[][] steinfeld;

  public Spielbrett() {
    steinfeld = new SpielStein[4][4];
  }

  public void setSpielStein(int zeile, int reihe, SpielStein stein) {
    steinfeld[zeile][reihe] = stein;
  }

  public boolean istSpielBeendet() {                                                                                                        //pr√ºfen ob Spiel gewonnen
    boolean result = false;

    result = istHorizontalBeendet(result);

    if (!result) {
      result = this.istVertikalBeendet(result);
    }

    if (!result) {
      result = this.istDiagonalBeendet(result);
    }

    return result;
  }

  public SpielStein[][] getSteinfeld() {
    return this.steinfeld;
  }

  private boolean istDiagonalBeendet(boolean result) {                                                                //Diagonale pr√ºfen
    int i = 0;
    result = this.istLinieBeendet(steinfeld[i][i++], steinfeld[i][i++],
                                  steinfeld[i][i++], steinfeld[i][i]);
    if (!result) {
      int length = steinfeld.length - 1;
      result = this.istLinieBeendet(steinfeld[i][length - i--],
                                    steinfeld[i][length - i--], steinfeld[i][length - i--],
                                    steinfeld[i][length - i]);
    }
    return result;
  }

  private boolean istVertikalBeendet(boolean result) {                                                                //Vertikale pr√ºfen
    for (SpielStein[] steinReihe : steinfeld) {
      result = this.istLinieBeendet(steinReihe[0], steinReihe[1],
                                    steinReihe[2], steinReihe[3]);
      if (result) {
        break;
      }
    }
    return result;
  }

  private boolean istHorizontalBeendet(boolean result) {                                                                //Horizontale pr√ºfen
    for (int i = 0; i < steinfeld.length; i++) {
      result = this.istLinieBeendet(steinfeld[0][i], steinfeld[1][i],
                                    steinfeld[2][i], steinfeld[3][i]);
      if (result) {
        break;
      }
    }
    return result;
  }

  private boolean istLinieBeendet(SpielStein s1, SpielStein s2, SpielStein s3, SpielStein s4) {                //pr√ºfen ob eine Linie existiert
    boolean result = false;
    if (s1 != null && s2 != null && s3 != null && s4 != null) {
      boolean istGr¸n = istGr¸nGleich(s1, s2, s3, s4);
      boolean istKlein = istKleinGleich(s1, s2, s3, s4);
      boolean istLoch = istLochGleich(s1, s2, s3, s4);
      boolean istRund = IstRundGleich(s1, s2, s3, s4);
      result = (istGr¸n || istKlein || istLoch || istRund);
    }
    return result;
  }

  private boolean istGr¸nGleich(SpielStein s1, SpielStein s2, SpielStein s3, SpielStein s4) {                        //Attribute pr√ºfen
    return s1.isIstGruen() == s2.isIstGruen()
           && s1.isIstGruen() == s3.isIstGruen()
           && s1.isIstGruen() == s4.isIstGruen()
           && s2.isIstGruen() == s3.isIstGruen()
           && s2.isIstGruen() == s4.isIstGruen()
           && s3.isIstGruen() == s4.isIstGruen();
  }

  private boolean istKleinGleich(SpielStein s1, SpielStein s2, SpielStein s3, SpielStein s4) {
    return s1.isIstKlein() == s2.isIstKlein()
           && s1.isIstKlein() == s3.isIstKlein()
           && s1.isIstKlein() == s4.isIstKlein()
           && s2.isIstKlein() == s3.isIstKlein()
           && s2.isIstKlein() == s4.isIstKlein()
           && s3.isIstKlein() == s4.isIstKlein();
  }

  private boolean istLochGleich(SpielStein s1, SpielStein s2, SpielStein s3, SpielStein s4) {
    return s1.isIstLoch() == s2.isIstLoch()
           && s1.isIstLoch() == s3.isIstLoch()
           && s1.isIstLoch() == s4.isIstLoch()
           && s2.isIstLoch() == s3.isIstLoch()
           && s2.isIstLoch() == s4.isIstLoch()
           && s3.isIstLoch() == s4.isIstLoch();
  }

  private boolean IstRundGleich(SpielStein s1, SpielStein s2, SpielStein s3, SpielStein s4) {
    return s1.isIstRund() == s2.isIstRund()
           && s1.isIstRund() == s3.isIstRund()
           && s1.isIstRund() == s4.isIstRund()
           && s2.isIstRund() == s3.isIstRund()
           && s2.isIstRund() == s4.isIstRund()
           && s3.isIstRund() == s4.isIstRund();
  }

  public boolean isUnentschieden() {

    boolean unentschieden = true;

    for (SpielStein[] spielSteins : steinfeld) {
      for (SpielStein spielStein : spielSteins) {
        if (spielStein == null) {
          unentschieden = false;
        }
      }
    }


    return unentschieden;
  }
}
