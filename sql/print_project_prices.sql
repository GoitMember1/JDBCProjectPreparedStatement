SELECT
    p.NAME,
    SUM(w.SALARY * (DATEDIFF(p.FINISH_DATE, p.START_DATE) / 30)) AS PRICE
FROM
    project p
JOIN
    project_worker pw ON p.ID = pw.PROJECT_ID
JOIN
    worker w ON pw.WORKER_ID = w.ID
GROUP BY
    p.ID
ORDER BY
    PRICE DESC;