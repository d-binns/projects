
(define partition-targets (lambda (L sum1 sum2 target1 target2)
    (cond
      (;base case
       (null? L)
       ;if we reached base case return the following
       (and (and (and (not (= sum1 0)) (= sum1 target1)) (not (= sum2 0))) (= sum2 target2));
       );end base case
      (else
       (or
        (partition-targets (cdr L) (+ sum1 (car L)) sum2 target1 target2)
        (partition-targets (cdr L) sum1 (+ sum2 (car L)) target1 target2)
        (partition-targets (cdr L) (+ sum1 (car L)) sum2 target1 (car L))
        (partition-targets (cdr L) sum1 (+ sum2 (car L)) (car L) target2)
        );end or 
       );end else
      );end cond
    );end lambda
  )
