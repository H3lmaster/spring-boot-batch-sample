SELECT ip, count(1) FROM parserbatch.log 
where date between @startDate and @endDate
group by ip
having count(1) > @threshold;