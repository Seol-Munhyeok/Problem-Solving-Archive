SELECT
    b.title,
    b.board_id,
    r.reply_id,
    r.writer_id,
    r.contents,
    r.created_date
FROM used_goods_board AS b
     JOIN used_goods_reply AS r
       ON b.board_id = r.board_id
WHERE b.created_date >= '2022-10-01'
AND   b.created_date < '2022-11-01'
ORDER BY r.created_date,
         b.title;