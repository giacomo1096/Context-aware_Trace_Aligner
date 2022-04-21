(define (problem Align) (:domain Mining)
(:objects
t0 - state
t1 - state
t2 - state
t3 - state
t4 - state
t5 - state
t6 - state
t7 - state
t8 - state
t9 - state
s_0_1 - state
s_0_0 - state
s_1_1 - state
s_1_0 - state
s_2_0 - state
s_2_2 - state
s_2_1 - state
s_3_0 - state
s_3_2 - state
s_3_1 - state
s_4_0 - state
s_4_2 - state
s_4_1 - state
s_5_0 - state
s_5_1 - state
s_6_0 - state
s_6_1 - state
)
(:init
(currstate t0)
(currstate s_0_1)
(currstate s_1_1)
(currstate s_2_0)
(currstate s_3_0)
(currstate s_4_0)
(currstate s_5_0)
(currstate s_6_0)
(= (total-cost) 0)
)
(:goal
(and
(currstate t9)
(currstate s_0_0)
(currstate s_1_0)
(currstate s_2_0)
(currstate s_3_0)
(currstate s_4_0)
))
(:metric minimize (total-cost))
)