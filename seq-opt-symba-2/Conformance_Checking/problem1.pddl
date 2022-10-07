(define (problem Align) (:domain Mining)
(:objects
t0 - state
t1 - state
t2 - state
t3 - state
t4 - state
s_0_3 - state
s_0_2 - state
s_0_0 - state
s_0_1 - state
s_0_abstract - state
s_1_1 - state
s_1_0 - state
s_2_0 - state
s_2_1 - state
)
(:init
(currstate t0)
(currstate s_0_3)
(currstate s_1_1)
(currstate s_2_0)
(= (total-cost) 0)
)
(:goal
(and
(currstate t4)
(currstate s_0_abstract)
(currstate s_1_0)
))
(:metric minimize (total-cost))
)