
(define partition-targets (lambda (L sum1 sum2 target1 target2)
    (cond
      (;base case
       (null? L)
       ;if we reached base case return the following
       (and (and (and (not (= sum1 0)) (= sum1 target1)) (not (= sum1 0))) (= sum2 target2));
       );end base case
      (else
       (or
        (partition-targets (cdr L) (+ sum1 (car L)) sum2 target1 target2 )
        (partition-targets (cdr L) sum1 (+ sum2 (car L)) target1 target2)
        (partition-targets (cdr L) (+ sum1 (car L)) sum2 target1 (car L))
        (partition-targets (cdr L) sum1 (+ sum2 (car L)) (car L) target2)
        );end or 
       );end else
      );end cond
    );end lambda
  )

(define list-target
  (lambda (lst sum target)
    (cond
      (;base case
       (null? lst)
       ;if we reached base case return the following
       (and (not (= sum 0)) (= sum target))
       );end base case
      (else
       (or
        (list-target (cdr lst) (+ sum (car lst)) target)
        (list-target (cdr lst) sum (car lst))
        (list-target (cdr lst) sum target)
        );end or
       );end else
      );end cond
    );end lambda
  )
(define add (lambda (data)
   (cond
     (;base case
      (null? 

  )
      )
     )))

(define size
  (lambda (lst)
    (cond ((null? lst) 0)
          (else (+ (size (cdr lst)) 1)))))

(define count
  (lambda (lst counter target)
  (cond ((null? lst))
        ((= counter 3) (display counter))
      
    (else
     (cond
       ((= (car lst) target)
        (count (cdr lst) (+ counter 1) target)
        )
    (else
     (count (cdr lst) counter target)
     ))
        
 ))))

(define increment
  (lambda (x)
    (+ x 1)))


    