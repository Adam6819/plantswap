PlantSwap
För att köra den här datan så krävs det Intelji, Postman och MongoDB compass

affärsregler
En användare kan inte ha mer än 10 aktiva annonser samtidigt

Växter markerade för byte kan endast bytas mot andra växter, inte säljas

Vid byte måste båda parter godkänna bytet innan det genomförs

Prissatta växter måste ha ett fast pris mellan 50-1000 kr

Begränsningar / förbättringar
Begränsning: TransactionController patchMapping saknar setters och getters Förbättringar: Sätt setters och getters för att undvika att data körs över.

Begränsningar: Transactions är inte dokumenterad i Postman för att data inte ska försvinna Förbättringar: Testa transaction i Postman för att säker ställa att det fungerar

Begränsningar: Det saknas en gräns på hur många aktiva annonser en användare får ha. Förbätringar: inkludera annoteringar som Min Max för att sedan sätta ett värde på min/max

Bergränsingar: Funktion saknas vid byte av växter mellan två användare så krävs det att båda bekräftar transactionen Förbättringar: Skapa en metod där en transaction sker endast om två användare bekräftar transactionen

Postman
https://documenter.getpostman.com/view/40758759/2sAYX3rip7