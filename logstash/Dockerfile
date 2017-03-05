FROM logstash:2-alpine

COPY logstash.conf /usr/share/logstash/pipeline/logstash.conf

CMD ["-f", "/usr/share/logstash/pipeline/logstash.conf"]