    SELECT SORDERP.SOHNUM_0 AS SO_SOHNUM,
        SORDERP.SOPLIN_0 AS SO_SOPLIN,
        SORDERP.ITMREF_0 AS SO_ITMREF,
        SORDERP.ITMDES_0 AS SO_ITMDESC,
        SORDERQ.QTY_0 AS SO_QTY,
        SORDERP.SAU_0 AS SO_SAU,
        SORDERQ.BPCORD_0 AS SO_BPCORD,
        SORDERQ.ORDDAT_0 AS SO_ORDDAT,
        SORDERQ.DEMDLVDAT_0 AS SO_DEMDLVDAT,
        SORDERQ.YSOQ_DLIVP_0 AS SO_SHIDATP,
        SORDERQ.CREDAT_0 AS SO_CREDAT,
        SORDERQ.CREUSR_0 AS SO_CREUSR,
        SORDERQ.YSOH_PJT_0 AS SO_YSOH_PJT,
        SORDERQ.YSOQ_STA_0 AS SO_YSOQ_STA,
        MFGMAT.MFGNUM_0 AS WO_MFGNUM,
        MFGMAT.BOMSEQ_0 AS WO_BOMSEQ,
        MFGMAT.ITMREF_0 AS WO_ITMREF,
        MFGMAT.BOMQTY_0 AS WO_BOMQTY,
        MFGMAT.BOMUOM_0 AS WO_BOMUOM,
        MFGMAT.SHTQTY_0 AS WO_SHIQTY,
        MFGMAT.ALLQTY_0 AS WO_ALLQTY,
        MFGMAT.CREDAT_0 AS WO_CREDAT,
        MFGMAT.CREUSR_0 AS WO_CREUSR,
        PORDERP.POHNUM_0 AS PO_POHNUM,
        PORDERP.POPLIN_0 AS PO_POPLIN,
        PORDERP.PJT_0 AS PO_PJT,
        PORDERP.ITMREF_0 AS PO_ITMREF,
        PORDERP.ITMDES_0 AS PO_ITMDESC,
        PORDERQ.QTYPUU_0 AS PO_QTYPUU,
        PORDERQ.PUU_0 AS PO_PUU,
        PORDERQ.BPSNUM_0 AS PO_BPSNUM,
        IIF(PORDERQ.LINOCNDAT_0='1753-01-01',NULL,PORDERQ.LINOCNDAT_0) AS PO_LINOCNDATA,
        PORDERQ.EXTRCPDAT_0 AS PO_EXTRCPDAT,
        PORDERP.CREDAT_0 AS PO_CREDAT,
        PORDERP.CREUSR_0 AS PO_CREUSR,
        PRECEIPTD.PTHNUM_0 AS PT_PTHNUM,
        PRECEIPTD.PTDLIN_0 AS PT_PTDLIN,
        PRECEIPTD.QTYPUU_0 AS PT_QTYPUU,
        PRECEIPTD.PUU_0 AS PT_PUU,
        PRECEIPTD.RCPDAT_0 AS PT_REPDAT,
        PRECEIPTD.CREUSR_0 AS PT_CREUSR
    FROM EXPLOIT.SORDERQ AS SORDERQ
        LEFT JOIN EXPLOIT.SORDERP AS SORDERP
        ON SORDERP.SOHNUM_0 = SORDERQ.SOHNUM_0
            AND SORDERP.SOPLIN_0 = SORDERQ.SOPLIN_0
        LEFT JOIN EXPLOIT.MFGITM AS MFGITM
        ON MFGITM.PJT_0 = SORDERQ.YSOH_PJT_0
        LEFT JOIN EXPLOIT.MFGMAT AS MFGMAT
        ON MFGITM.MFGNUM_0 = MFGMAT.MFGNUM_0
            AND MFGITM.MFGLIN_0 = MFGMAT.MFGLIN_0
        LEFT JOIN EXPLOIT.PORDERP AS PORDERP
        ON SORDERQ.SALFCY_0=PORDERP.PRHFCY_0
            AND SORDERQ.YSOH_PJT_0 = PORDERP.PJT_0
            AND (SORDERQ.ITMREF_0 = PORDERP.ITMREF_0
            OR MFGMAT.ITMREF_0=PORDERP.ITMREF_0)
        LEFT JOIN EXPLOIT.PORDERQ AS PORDERQ
        ON PORDERP.POHNUM_0=PORDERQ.POHNUM_0
            AND PORDERP.POPLIN_0=PORDERQ.POPLIN_0
        LEFT JOIN EXPLOIT.PRECEIPTD AS PRECEIPTD
        ON PORDERP.POHNUM_0=PRECEIPTD.POHNUM_0
            AND PORDERP.POPLIN_0=PRECEIPTD.POPLIN_0
    WHERE SORDERQ.SOQSTA_0<>3
        AND SORDERQ.SALFCY_0=:facility

UNION

    SELECT SORDERP.SOHNUM_0 AS SO_SOHNUM,
        SORDERP.SOPLIN_0 AS SO_SOPLIN,
        SORDERP.ITMREF_0 AS SO_ITMREF,
        SORDERP.ITMDES_0 AS SO_ITMDESC,
        SORDERQ.QTY_0 AS SO_QTY,
        SORDERP.SAU_0 AS SO_SAU,
        SORDERQ.BPCORD_0 AS SO_BPCORD,
        SORDERQ.ORDDAT_0 AS SO_ORDDAT,
        SORDERQ.DEMDLVDAT_0 AS SO_DEMDLVDAT,
        SORDERQ.YSOQ_DLIVP_0 AS SO_SHIDATP,
        SORDERQ.CREDAT_0 AS SO_CREDAT,
        SORDERQ.CREUSR_0 AS SO_CREUSR,
        SORDERQ.YSOH_PJT_0 AS SO_YSOH_PJT,
        SORDERQ.YSOQ_STA_0 AS SO_YSOQ_STA,
        MFGMAT.MFGNUM_0 AS WO_MFGNUM,
        MFGMAT.BOMSEQ_0 AS WO_BOMSEQ,
        MFGMAT.ITMREF_0 AS WO_ITMREF,
        MFGMAT.BOMQTY_0 AS WO_BOMQTY,
        MFGMAT.BOMUOM_0 AS WO_BOMUOM,
        MFGMAT.SHTQTY_0 AS WO_SHIQTY,
        MFGMAT.ALLQTY_0 AS WO_ALLQTY,
        MFGMAT.CREDAT_0 AS WO_CREDAT,
        MFGMAT.CREUSR_0 AS WO_CREUSR,
        PORDERP.POHNUM_0 AS PO_POHNUM,
        PORDERP.POPLIN_0 AS PO_POPLIN,
        PORDERP.PJT_0 AS PO_PJT,
        PORDERP.ITMREF_0 AS PO_ITMREF,
        PORDERP.ITMDES_0 AS PO_ITMDESC,
        PORDERQ.QTYPUU_0 AS PO_QTYPUU,
        PORDERQ.PUU_0 AS PO_PUU,
        PORDERQ.BPSNUM_0 AS PO_BPSNUM,
        IIF(PORDERQ.LINOCNDAT_0='1753-01-01',NULL,PORDERQ.LINOCNDAT_0) AS PO_LINOCNDATA,
        PORDERQ.EXTRCPDAT_0 AS PO_EXTRCPDAT,
        PORDERP.CREDAT_0 AS PO_CREDAT,
        PORDERP.CREUSR_0 AS PO_CREUSR,
        PRECEIPTD.PTHNUM_0 AS PT_PTHNUM,
        PRECEIPTD.PTDLIN_0 AS PT_PTDLIN,
        PRECEIPTD.QTYPUU_0 AS PT_QTYPUU,
        PRECEIPTD.PUU_0 AS PT_PUU,
        PRECEIPTD.RCPDAT_0 AS PT_REPDAT,
        PRECEIPTD.CREUSR_0 AS PT_CREUSR
    FROM EXPLOIT.SORDERQ SORDERQ
        LEFT JOIN EXPLOIT.SORDERP SORDERP
        ON SORDERP.SOHNUM_0=SORDERQ.SOHNUM_0
            AND SORDERP.SOPLIN_0=SORDERQ.SOPLIN_0
        LEFT JOIN EXPLOIT.MFGITM MFGITM
        ON MFGITM.PJT_0=SORDERQ.YSOH_PJT_0
        LEFT JOIN EXPLOIT.MFGMAT
        ON MFGITM.MFGNUM_0 = MFGMAT.MFGNUM_0
            AND MFGITM.MFGLIN_0 = MFGMAT.MFGLIN_0
        LEFT JOIN EXPLOIT.PORDERP PORDERP
        ON SORDERQ.SALFCY_0=PORDERP.PRHFCY_0
            AND (SORDERQ.ITMREF_0=PORDERP.ITMREF_0
            OR MFGMAT.ITMREF_0=PORDERP.ITMREF_0)
            AND LEN(PORDERP.PJT_0)=5
        INNER JOIN EXPLOIT.PORDERQ PORDERQ
        ON PORDERP.POHNUM_0=PORDERQ.POHNUM_0
            AND PORDERP.POPLIN_0=PORDERQ.POPLIN_0
            AND PORDERQ.LINCLEFLG_0=1
        LEFT JOIN EXPLOIT.PRECEIPTD PRECEIPTD
        ON PORDERP.POHNUM_0=PRECEIPTD.POHNUM_0
            AND PORDERP.POPLIN_0=PRECEIPTD.POPLIN_0
    WHERE SORDERQ.SOQSTA_0<>3
        AND SORDERQ.SALFCY_0=:facility
ORDER BY  SO_SHIDATP ASC, SO_SOHNUM ASC, SO_SOPLIN ASC,WO_BOMSEQ ASC, PO_POHNUM ASC, PO_POPLIN ASC 

