(define (domain Mining)
(:requirements :typing :equality)
(:types state)

(:predicates
(currstate ?s - state)
)

(:functions
(total-cost)
)

(:action add-a-ct0
:precondition (currstate s_0_3) 
:effect (and (not (currstate s_0_3)) (currstate s_0_2)  (increase (total-cost) 1))
)

(:action add-a-ct1
:precondition (currstate s_0_0) 
:effect (and (not (currstate s_0_0)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-b-ct2
:precondition (currstate s_0_3) 
:effect (and (not (currstate s_0_3)) (currstate s_0_0)  (increase (total-cost) 1))
)

(:action add-b-ct3
:precondition (currstate s_0_2) 
:effect (and (not (currstate s_0_2)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-c-ct4
:precondition (currstate s_1_1) 
:effect (and (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-d-ct5
:precondition (currstate s_2_1) 
:effect (and (currstate s_2_1)  (increase (total-cost) 7))
)

(:action add-d-ct6
:precondition (currstate s_2_0) 
:effect (and (currstate s_2_0)  (increase (total-cost) 6))
)

(:action sync-a-ct0
:precondition (and (currstate t2) (currstate s_0_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct0
:precondition (and (currstate t3) (currstate s_0_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct1
:precondition (and (currstate t2) (currstate s_0_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-a-ct1
:precondition (and (currstate t3) (currstate s_0_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-a-ct2
:precondition (and (currstate t2) (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct2
:precondition (and (currstate t3) (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct3
:precondition (and (currstate t2) (currstate s_0_3) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct3
:precondition (and (currstate t3) (currstate s_0_3) (currstate s_1_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct4
:precondition (and (currstate t2) (currstate s_0_0) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct4
:precondition (and (currstate t3) (currstate s_0_0) (currstate s_1_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct5
:precondition (and (currstate t0) (currstate s_0_3) (not (currstate s_1_1)) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct5
:precondition (and (currstate t1) (currstate s_0_3) (not (currstate s_1_1)) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct6
:precondition (and (currstate t0) (currstate s_0_2) (not (currstate s_1_1)) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct6
:precondition (and (currstate t1) (currstate s_0_2) (not (currstate s_1_1)) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct7
:precondition (and (currstate t0) (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct7
:precondition (and (currstate t1) (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct8
:precondition (and (currstate t0) (currstate s_2_2) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct8
:precondition (and (currstate t1) (currstate s_2_2) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct9
:precondition (and (currstate t0) (currstate s_2_3) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct9
:precondition (and (currstate t1) (currstate s_2_3) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct10
:precondition (and (currstate t0) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct10
:precondition (and (currstate t1) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct11
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct11
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct12
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_2_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct12
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_2_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct13
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_2_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct13
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_2_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct14
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_2_1) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct14
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_2_1) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct15
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct15
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct16
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_2_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct16
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_2_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct17
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_2_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct17
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_2_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct18
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_2_1) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct18
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_2_1) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct19
:precondition (and (currstate t0) (currstate s_1_1) (currstate s_2_2) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct19
:precondition (and (currstate t1) (currstate s_1_1) (currstate s_2_2) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct20
:precondition (and (currstate t0) (currstate s_1_1) (currstate s_2_3) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct20
:precondition (and (currstate t1) (currstate s_1_1) (currstate s_2_3) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct21
:precondition (and (currstate t0) (currstate s_1_1) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct21
:precondition (and (currstate t1) (currstate s_1_1) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct22
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_1) (currstate s_2_2) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct22
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_1_1) (currstate s_2_2) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct23
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_1) (currstate s_2_3) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct23
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_1_1) (currstate s_2_3) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct24
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_1) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct24
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_1_1) (currstate s_2_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct25
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_1_1) (currstate s_2_2) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct25
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_1_1) (currstate s_2_2) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct26
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_1_1) (currstate s_2_3) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct26
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_1_1) (currstate s_2_3) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct27
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_1_1) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct27
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_1_1) (currstate s_2_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct28
:precondition (and (currstate t2) (currstate s_0_3) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct28
:precondition (and (currstate t3) (currstate s_0_3) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct29
:precondition (and (currstate t2) (currstate s_0_0) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-a-ct29
:precondition (and (currstate t3) (currstate s_0_0) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-b-ct30
:precondition (and (currstate t0) (currstate s_0_3) (not (currstate s_2_2)) (not (currstate s_2_1)) (not (currstate s_2_3)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct30
:precondition (and (currstate t1) (currstate s_0_3) (not (currstate s_2_2)) (not (currstate s_2_1)) (not (currstate s_2_3)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct31
:precondition (and (currstate t0) (currstate s_0_2) (not (currstate s_2_2)) (not (currstate s_2_1)) (not (currstate s_2_3)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct31
:precondition (and (currstate t1) (currstate s_0_2) (not (currstate s_2_2)) (not (currstate s_2_1)) (not (currstate s_2_3)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct32
:precondition (and (currstate t0) (currstate s_2_2) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct32
:precondition (and (currstate t1) (currstate s_2_2) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct33
:precondition (and (currstate t0) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct33
:precondition (and (currstate t1) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct34
:precondition (and (currstate t0) (currstate s_2_3) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct34
:precondition (and (currstate t1) (currstate s_2_3) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct35
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_2_2) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct35
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_2_2) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct36
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct36
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_2_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct37
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_2_3) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct37
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_2_3) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct38
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_2_2) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct38
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_2_2) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-b-ct39
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct39
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_2_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct40
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_2_3) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-b-ct40
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_2_3) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-a-ct42
:precondition (and (currstate t2) (currstate s_0_3) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct42
:precondition (and (currstate t3) (currstate s_0_3) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct43
:precondition (and (currstate t2) (currstate s_0_0) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-a-ct43
:precondition (and (currstate t3) (currstate s_0_0) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-b-ct44
:precondition (and (currstate t0) (currstate s_0_3) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct44
:precondition (and (currstate t1) (currstate s_0_3) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct45
:precondition (and (currstate t0) (currstate s_0_2) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct45
:precondition (and (currstate t1) (currstate s_0_2) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-a-ct47
:precondition (and (currstate t2) (currstate s_0_3) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct47
:precondition (and (currstate t3) (currstate s_0_3) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct48
:precondition (and (currstate t2) (currstate s_0_0) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-a-ct48
:precondition (and (currstate t3) (currstate s_0_0) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-b-ct49
:precondition (and (currstate t0) (currstate s_0_3) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct49
:precondition (and (currstate t1) (currstate s_0_3) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct50
:precondition (and (currstate t0) (currstate s_0_2) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct50
:precondition (and (currstate t1) (currstate s_0_2) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct51
:precondition (and (currstate t0) (currstate s_2_0) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_0)) (currstate s_2_1) )
)

(:action sync-b-ct51
:precondition (and (currstate t1) (currstate s_2_0) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_0)) (currstate s_2_1) )
)

(:action sync-b-ct52
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_2_0) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_0)) (currstate s_2_1) )
)

(:action sync-b-ct52
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_2_0) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_0)) (currstate s_2_1) )
)

(:action sync-b-ct53
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_2_0) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_0)) (currstate s_2_1) )
)

(:action sync-b-ct53
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_2_0) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_0)) (currstate s_2_1) )
)

(:action sync-b-t0t1
:precondition (and (currstate t0) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1))) 

(:action sync-b-t1t2
:precondition (and (currstate t1) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2))) 

(:action sync-a-t2t3
:precondition (and (currstate t2) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate t2)) (currstate t3))) 

(:action sync-a-t3t4
:precondition (and (currstate t3) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate t3)) (currstate t4))) 

(:action del-a-ct0
:precondition (and (currstate t2) (currstate s_2_1) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 4))
) 

(:action del-a-ct0
:precondition (and (currstate t3) (currstate s_2_1) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 4))
) 

(:action del-a-ct1
:precondition (and (currstate t2) (currstate s_2_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_3)  (increase (total-cost) 5))
) 

(:action del-a-ct1
:precondition (and (currstate t3) (currstate s_2_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_3)  (increase (total-cost) 5))
) 

(:action del-a-ct2
:precondition (and (currstate t2) (currstate s_2_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (currstate s_2_3)  (increase (total-cost) 5))
) 

(:action del-a-ct2
:precondition (and (currstate t3) (currstate s_2_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (currstate s_2_3)  (increase (total-cost) 5))
) 

(:action del-a-ct3
:precondition (and (currstate t2) (currstate s_2_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
) 

(:action del-a-ct3
:precondition (and (currstate t3) (currstate s_2_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
) 

(:action del-a-ct4
:precondition (and (currstate t2) (currstate s_1_1) (not (currstate s_2_1)) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-a-ct4
:precondition (and (currstate t3) (currstate s_1_1) (not (currstate s_2_1)) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-a-ct5
:precondition (and (currstate t2) (currstate s_2_1) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_1)) (currstate s_2_2) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 4))
) 

(:action del-a-ct5
:precondition (and (currstate t3) (currstate s_2_1) (currstate s_1_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_1)) (currstate s_2_2) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 4))
) 

(:action del-a-ct6
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_3) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 5))
) 

(:action del-a-ct6
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_1_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_3) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 5))
) 

(:action del-a-ct7
:precondition (and (currstate t2) (currstate s_2_3) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (currstate s_2_3) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 5))
) 

(:action del-a-ct7
:precondition (and (currstate t3) (currstate s_2_3) (currstate s_1_1) )
:effect (and (not (currstate t3)) (currstate t4) (currstate s_2_3) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 5))
) 

(:action del-a-ct8
:precondition (and (currstate t2) (currstate s_2_0) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_0)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-a-ct8
:precondition (and (currstate t3) (currstate s_2_0) (currstate s_1_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_0)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-b-ct9
:precondition (and (currstate t0) (currstate s_1_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-b-ct9
:precondition (and (currstate t1) (currstate s_1_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-a-ct10
:precondition (and (currstate t2) (currstate s_2_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 3))
) 

(:action del-a-ct10
:precondition (and (currstate t3) (currstate s_2_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 3))
) 

(:action del-a-ct11
:precondition (and (currstate t2) (currstate s_2_2) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_3)  (increase (total-cost) 4))
) 

(:action del-a-ct11
:precondition (and (currstate t3) (currstate s_2_2) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_3)  (increase (total-cost) 4))
) 

(:action del-a-ct12
:precondition (and (currstate t2) (currstate s_2_3) )
:effect (and (not (currstate t2)) (currstate t3) (currstate s_2_3)  (increase (total-cost) 4))
) 

(:action del-a-ct12
:precondition (and (currstate t3) (currstate s_2_3) )
:effect (and (not (currstate t3)) (currstate t4) (currstate s_2_3)  (increase (total-cost) 4))
) 

(:action del-a-ct13
:precondition (and (currstate t2) (currstate s_2_0) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
) 

(:action del-a-ct13
:precondition (and (currstate t3) (currstate s_2_0) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
) 

(:action del-b-t0t1
:precondition (currstate t0) 
:effect (and (not (currstate t0)) (currstate t1)  (increase (total-cost) 1))
) 

(:action del-b-t1t2
:precondition (currstate t1) 
:effect (and (not (currstate t1)) (currstate t2)  (increase (total-cost) 1))
) 

(:action del-a-t2t3
:precondition (currstate t2) 
:effect (and (not (currstate t2)) (currstate t3)  (increase (total-cost) 1))
) 

(:action del-a-t3t4
:precondition (currstate t3) 
:effect (and (not (currstate t3)) (currstate t4)  (increase (total-cost) 1))
) 

(:action goto-abstract_states-cs0
:precondition (and (currstate t4) (currstate s_0_2) (currstate s_1_0) )
:effect (and (currstate s_0_abstract) (not (currstate s_0_2)) )
)

(:action goto-abstract_states-cs1
:precondition (and (currstate t4) (currstate s_0_0) (currstate s_1_0) )
:effect (and (currstate s_0_abstract) (not (currstate s_0_0)) )
)

)