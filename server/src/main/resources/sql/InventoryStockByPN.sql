SELECT 
    STOCK.STOFCY_0 AS StockSite,
    STOCK.ITMREF_0 AS PN,
    COUNT(STOCK.QTYSTU_0) AS Qty
FROM EXPLOIT.STOCK
    LEFT JOIN EXPLOIT.ITMMASTER
    ON ITMMASTER.ITMREF_0=STOCK.ITMREF_0
WHERE ITMMASTER.XITM_RACIN_0=:pnRoot 
GROUP BY STOCK.STOFCY_0,
         STOCK.ITMREF_0