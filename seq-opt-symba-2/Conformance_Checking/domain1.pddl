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
:precondition (and (currstate s_0_3) (not (currstate s_1_2)) (not (currstate s_2_1)) (not (currstate s_2_0)) (not (currstate s_2_2)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2)  (increase (total-cost) 1))
)

(:action add-a-ct1
:precondition (and (currstate s_1_2) (not (currstate s_0_3)) (not (currstate s_0_0)) (not (currstate s_2_1)) (not (currstate s_2_0)) (not (currstate s_2_2)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-a-ct2
:precondition (and (currstate s_0_0) (not (currstate s_1_2)) (not (currstate s_2_1)) (not (currstate s_2_0)) (not (currstate s_2_2)) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-a-ct3
:precondition (and (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) )
:effect (and (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct4
:precondition (and (currstate s_2_0) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) )
:effect (and (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-a-ct5
:precondition (and (currstate s_2_2) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) )
:effect (and (currstate s_2_2)  (increase (total-cost) 3))
)

(:action add-a-ct6
:precondition (and (currstate s_0_3) (currstate s_1_2) (not (currstate s_2_1)) (not (currstate s_2_0)) (not (currstate s_2_2)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-a-ct7
:precondition (and (currstate s_0_3) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct8
:precondition (and (currstate s_0_3) (currstate s_2_0) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-a-ct9
:precondition (and (currstate s_0_3) (currstate s_2_2) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (currstate s_2_2)  (increase (total-cost) 3))
)

(:action add-a-ct10
:precondition (and (currstate s_1_2) (currstate s_0_0) (not (currstate s_2_1)) (not (currstate s_2_0)) (not (currstate s_2_2)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-a-ct11
:precondition (and (currstate s_1_2) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct12
:precondition (and (currstate s_1_2) (currstate s_2_0) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-a-ct13
:precondition (and (currstate s_1_2) (currstate s_2_2) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (currstate s_2_2)  (increase (total-cost) 3))
)

(:action add-a-ct14
:precondition (and (currstate s_0_0) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct15
:precondition (and (currstate s_0_0) (currstate s_2_0) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-a-ct16
:precondition (and (currstate s_0_0) (currstate s_2_2) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1) (currstate s_2_2)  (increase (total-cost) 3))
)

(:action add-a-ct17
:precondition (and (currstate s_0_3) (currstate s_1_2) (currstate s_2_1) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct18
:precondition (and (currstate s_0_3) (currstate s_1_2) (currstate s_2_0) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-a-ct19
:precondition (and (currstate s_0_3) (currstate s_1_2) (currstate s_2_2) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (currstate s_2_2)  (increase (total-cost) 3))
)

(:action add-a-ct20
:precondition (and (currstate s_1_2) (currstate s_0_0) (currstate s_2_1) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct21
:precondition (and (currstate s_1_2) (currstate s_0_0) (currstate s_2_0) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-a-ct22
:precondition (and (currstate s_1_2) (currstate s_0_0) (currstate s_2_2) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (currstate s_2_2)  (increase (total-cost) 3))
)

(:action add-b-ct23
:precondition (and (currstate s_0_3) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0)  (increase (total-cost) 1))
)

(:action add-b-ct24
:precondition (and (currstate s_1_2) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-b-ct25
:precondition (and (currstate s_0_2) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-b-ct26
:precondition (and (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_2)) )
:effect (and (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-b-ct27
:precondition (and (currstate s_0_3) (currstate s_1_2) (not (currstate s_2_1)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_2)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-b-ct28
:precondition (and (currstate s_0_3) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-b-ct29
:precondition (and (currstate s_1_2) (currstate s_0_2) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_2)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-b-ct30
:precondition (and (currstate s_1_2) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-b-ct31
:precondition (and (currstate s_0_2) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-b-ct32
:precondition (and (currstate s_0_3) (currstate s_1_2) (currstate s_2_1) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-b-ct33
:precondition (and (currstate s_1_2) (currstate s_0_2) (currstate s_2_1) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-c-ct34
:precondition (and (currstate s_1_0) (not (currstate s_4_0)) (not (currstate s_4_1)) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_0)) (currstate s_1_2)  (increase (total-cost) 1))
)

(:action add-c-ct35
:precondition (and (currstate s_1_2) (not (currstate s_4_0)) (not (currstate s_4_1)) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-c-ct36
:precondition (and (currstate s_4_0) (not (currstate s_1_0)) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (currstate s_4_0)  (increase (total-cost) 3))
)

(:action add-c-ct37
:precondition (and (currstate s_4_1) (not (currstate s_1_0)) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (currstate s_4_1)  (increase (total-cost) 5))
)

(:action add-c-ct38
:precondition (and (currstate s_2_1) (not (currstate s_1_0)) (not (currstate s_1_2)) (not (currstate s_4_0)) (not (currstate s_4_1)) )
:effect (and (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-c-ct39
:precondition (and (currstate s_1_0) (currstate s_4_0) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_0)) (currstate s_1_2) (currstate s_4_0)  (increase (total-cost) 3))
)

(:action add-c-ct40
:precondition (and (currstate s_1_0) (currstate s_4_1) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_0)) (currstate s_1_2) (currstate s_4_1)  (increase (total-cost) 5))
)

(:action add-c-ct41
:precondition (and (currstate s_1_0) (currstate s_2_1) (not (currstate s_4_0)) (not (currstate s_4_1)) )
:effect (and (not (currstate s_1_0)) (currstate s_1_2) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-c-ct42
:precondition (and (currstate s_1_2) (currstate s_4_0) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (currstate s_4_0)  (increase (total-cost) 3))
)

(:action add-c-ct43
:precondition (and (currstate s_1_2) (currstate s_4_1) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (currstate s_4_1)  (increase (total-cost) 5))
)

(:action add-c-ct44
:precondition (and (currstate s_1_2) (currstate s_2_1) (not (currstate s_4_0)) (not (currstate s_4_1)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-c-ct45
:precondition (and (currstate s_4_0) (currstate s_2_1) (not (currstate s_1_0)) (not (currstate s_1_2)) )
:effect (and (currstate s_4_0) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 3))
)

(:action add-c-ct46
:precondition (and (currstate s_4_1) (currstate s_2_1) (not (currstate s_1_0)) (not (currstate s_1_2)) )
:effect (and (currstate s_4_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 5))
)

(:action add-c-ct47
:precondition (and (currstate s_1_0) (currstate s_4_0) (currstate s_2_1) )
:effect (and (not (currstate s_1_0)) (currstate s_1_2) (currstate s_4_0) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 3))
)

(:action add-c-ct48
:precondition (and (currstate s_1_0) (currstate s_4_1) (currstate s_2_1) )
:effect (and (not (currstate s_1_0)) (currstate s_1_2) (currstate s_4_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 5))
)

(:action add-c-ct49
:precondition (and (currstate s_1_2) (currstate s_4_0) (currstate s_2_1) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (currstate s_4_0) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 3))
)

(:action add-c-ct50
:precondition (and (currstate s_1_2) (currstate s_4_1) (currstate s_2_1) )
:effect (and (not (currstate s_1_2)) (currstate s_1_1) (currstate s_4_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 5))
)

(:action add-d-ct51
:precondition (and (currstate s_1_0) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_0)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-d-ct52
:precondition (and (currstate s_1_2) (not (currstate s_2_1)) )
:effect (and (not (currstate s_1_2)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-d-ct53
:precondition (and (currstate s_2_1) (not (currstate s_1_0)) (not (currstate s_1_2)) )
:effect (and (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-d-ct54
:precondition (and (currstate s_1_0) (currstate s_2_1) )
:effect (and (not (currstate s_1_0)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-d-ct55
:precondition (and (currstate s_1_2) (currstate s_2_1) )
:effect (and (not (currstate s_1_2)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action sync-a-ct0
:precondition (and (currstate t0) (currstate s_0_3) (not (currstate s_1_2)) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct1
:precondition (and (currstate t0) (currstate s_1_2) (not (currstate s_0_3)) (not (currstate s_0_0)) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) )
)

(:action sync-a-ct2
:precondition (and (currstate t0) (currstate s_0_0) (not (currstate s_1_2)) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-a-ct3
:precondition (and (currstate t0) (currstate s_3_1) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_1)) (currstate s_3_0) )
)

(:action sync-a-ct4
:precondition (and (currstate t0) (currstate s_3_2) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_0) )
)

(:action sync-a-ct5
:precondition (and (currstate t0) (currstate s_3_3) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_3)) (currstate s_3_0) )
)

(:action sync-a-ct6
:precondition (and (currstate t0) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct7
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_2) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) )
)

(:action sync-a-ct8
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_3_1) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_3_1)) (currstate s_3_0) )
)

(:action sync-a-ct9
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_3_2) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_3_2)) (currstate s_3_0) )
)

(:action sync-a-ct10
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_3_3) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_3_3)) (currstate s_3_0) )
)

(:action sync-a-ct11
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_2_1) (not (currstate s_1_2)) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct12
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_0_0) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-a-ct13
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_3_1) (not (currstate s_0_3)) (not (currstate s_0_0)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_1)) (currstate s_3_0) )
)

(:action sync-a-ct14
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_3_2) (not (currstate s_0_3)) (not (currstate s_0_0)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_2)) (currstate s_3_0) )
)

(:action sync-a-ct15
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_3_3) (not (currstate s_0_3)) (not (currstate s_0_0)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_3)) (currstate s_3_0) )
)

(:action sync-a-ct16
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_0)) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct17
:precondition (and (currstate t0) (currstate s_0_0) (currstate s_3_1) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_1)) (currstate s_3_0) )
)

(:action sync-a-ct18
:precondition (and (currstate t0) (currstate s_0_0) (currstate s_3_2) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_2)) (currstate s_3_0) )
)

(:action sync-a-ct19
:precondition (and (currstate t0) (currstate s_0_0) (currstate s_3_3) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_3)) (currstate s_3_0) )
)

(:action sync-a-ct20
:precondition (and (currstate t0) (currstate s_0_0) (currstate s_2_1) (not (currstate s_1_2)) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct21
:precondition (and (currstate t0) (currstate s_3_1) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_1)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct22
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct23
:precondition (and (currstate t0) (currstate s_3_3) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_3)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct24
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_2) (currstate s_3_1) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_1)) (currstate s_3_0) )
)

(:action sync-a-ct25
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_2) (currstate s_3_2) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_2)) (currstate s_3_0) )
)

(:action sync-a-ct26
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_2) (currstate s_3_3) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_3)) (currstate s_3_0) )
)

(:action sync-a-ct27
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_2) (currstate s_2_1) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct28
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_3_1) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_3_1)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct29
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_3_2) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct30
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_3_3) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_3_3)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct31
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_0_0) (currstate s_3_1) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_1)) (currstate s_3_0) )
)

(:action sync-a-ct32
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_0_0) (currstate s_3_2) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_2)) (currstate s_3_0) )
)

(:action sync-a-ct33
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_0_0) (currstate s_3_3) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_3)) (currstate s_3_0) )
)

(:action sync-a-ct34
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_0_0) (currstate s_2_1) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct35
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_3_1) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_1)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct36
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_3_2) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct37
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_3_3) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_3)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct38
:precondition (and (currstate t0) (currstate s_0_0) (currstate s_3_1) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_1)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct39
:precondition (and (currstate t0) (currstate s_0_0) (currstate s_3_2) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct40
:precondition (and (currstate t0) (currstate s_0_0) (currstate s_3_3) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_3)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct41
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_2) (currstate s_3_1) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_1)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct42
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_2) (currstate s_3_2) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct43
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_2) (currstate s_3_3) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_3_3)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct44
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_0_0) (currstate s_3_1) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_1)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct45
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_0_0) (currstate s_3_2) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct46
:precondition (and (currstate t0) (currstate s_1_2) (currstate s_0_0) (currstate s_3_3) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_3_3)) (currstate s_3_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct47
:precondition (and (currstate t3) (currstate s_0_3) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct48
:precondition (and (currstate t3) (currstate s_1_2) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_2)) (currstate s_1_1) )
)

(:action sync-b-ct49
:precondition (and (currstate t3) (currstate s_0_2) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct50
:precondition (and (currstate t3) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct51
:precondition (and (currstate t3) (currstate s_0_3) (currstate s_1_2) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_2)) (currstate s_1_1) )
)

(:action sync-b-ct52
:precondition (and (currstate t3) (currstate s_0_3) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct53
:precondition (and (currstate t3) (currstate s_1_2) (currstate s_0_2) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct54
:precondition (and (currstate t3) (currstate s_1_2) (currstate s_2_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct55
:precondition (and (currstate t3) (currstate s_0_2) (currstate s_2_1) (not (currstate s_1_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct56
:precondition (and (currstate t3) (currstate s_0_3) (currstate s_1_2) (currstate s_2_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-b-ct57
:precondition (and (currstate t3) (currstate s_1_2) (currstate s_0_2) (currstate s_2_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-c-ct58
:precondition (and (currstate t2) (currstate s_1_0) (not (currstate s_2_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_0)) (currstate s_1_2) )
)

(:action sync-c-ct59
:precondition (and (currstate t2) (currstate s_1_2) (not (currstate s_2_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_2)) (currstate s_1_1) )
)

(:action sync-c-ct60
:precondition (and (currstate t2) (currstate s_2_1) (not (currstate s_1_0)) (not (currstate s_1_2)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-c-ct61
:precondition (and (currstate t2) (currstate s_1_0) (currstate s_2_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_0)) (currstate s_1_2) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-c-ct62
:precondition (and (currstate t2) (currstate s_1_2) (currstate s_2_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_2)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-d-ct63
:precondition (and (currstate t1) (currstate s_1_0) (not (currstate s_2_1)) (not (currstate s_4_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_0)) (currstate s_1_1) )
)

(:action sync-d-ct64
:precondition (and (currstate t1) (currstate s_1_2) (not (currstate s_2_1)) (not (currstate s_4_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_2)) (currstate s_1_0) )
)

(:action sync-d-ct65
:precondition (and (currstate t1) (currstate s_2_1) (not (currstate s_1_0)) (not (currstate s_1_2)) (not (currstate s_4_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-d-ct66
:precondition (and (currstate t1) (currstate s_4_0) (not (currstate s_1_0)) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_4_0)) (currstate s_4_1) )
)

(:action sync-d-ct67
:precondition (and (currstate t1) (currstate s_1_0) (currstate s_2_1) (not (currstate s_4_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_0)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-d-ct68
:precondition (and (currstate t1) (currstate s_1_0) (currstate s_4_0) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_0)) (currstate s_1_1) (not (currstate s_4_0)) (currstate s_4_1) )
)

(:action sync-d-ct69
:precondition (and (currstate t1) (currstate s_1_2) (currstate s_2_1) (not (currstate s_4_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_2)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-d-ct70
:precondition (and (currstate t1) (currstate s_1_2) (currstate s_4_0) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_2)) (currstate s_1_0) (not (currstate s_4_0)) (currstate s_4_1) )
)

(:action sync-d-ct71
:precondition (and (currstate t1) (currstate s_2_1) (currstate s_4_0) (not (currstate s_1_0)) (not (currstate s_1_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_1)) (currstate s_2_0) (not (currstate s_4_0)) (currstate s_4_1) )
)

(:action sync-d-ct72
:precondition (and (currstate t1) (currstate s_1_0) (currstate s_2_1) (currstate s_4_0) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_0)) (currstate s_1_1) (not (currstate s_2_1)) (currstate s_2_0) (not (currstate s_4_0)) (currstate s_4_1) )
)

(:action sync-d-ct73
:precondition (and (currstate t1) (currstate s_1_2) (currstate s_2_1) (currstate s_4_0) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_2)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0) (not (currstate s_4_0)) (currstate s_4_1) )
)

(:action sync-a-t0t1
:precondition (and (currstate t0) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_0)) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1))) 

(:action sync-d-t1t2
:precondition (and (currstate t1) (not (currstate s_1_0)) (not (currstate s_1_2)) (not (currstate s_2_1)) (not (currstate s_4_0)) )
:effect (and (not (currstate t1)) (currstate t2))) 

(:action sync-c-t2t3
:precondition (and (currstate t2) (not (currstate s_1_0)) (not (currstate s_1_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t2)) (currstate t3))) 

(:action sync-b-t3t4
:precondition (and (currstate t3) (not (currstate s_0_3)) (not (currstate s_1_2)) (not (currstate s_0_2)) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4))) 

(:action del-a-ct0
:precondition (and (currstate t0) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
) 

(:action del-b-ct1
:precondition (and (currstate t3) (currstate s_3_1) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_1)) (currstate s_3_2)  (increase (total-cost) 3))
) 

(:action del-b-ct2
:precondition (and (currstate t3) (currstate s_2_1) (not (currstate s_3_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) (not (currstate s_3_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
) 

(:action del-b-ct3
:precondition (and (currstate t3) (currstate s_3_2) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_3)  (increase (total-cost) 4))
) 

(:action del-b-ct4
:precondition (and (currstate t3) (currstate s_3_3) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4) (currstate s_3_3)  (increase (total-cost) 4))
) 

(:action del-b-ct5
:precondition (and (currstate t3) (currstate s_3_0) (not (currstate s_2_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_0)) (currstate s_3_1)  (increase (total-cost) 1))
) 

(:action del-b-ct6
:precondition (and (currstate t3) (currstate s_3_1) (currstate s_2_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_1)) (currstate s_3_2) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 3))
) 

(:action del-b-ct7
:precondition (and (currstate t3) (currstate s_2_1) (currstate s_3_2) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_1)) (currstate s_2_0) (not (currstate s_3_2)) (currstate s_3_3)  (increase (total-cost) 4))
) 

(:action del-b-ct8
:precondition (and (currstate t3) (currstate s_2_1) (currstate s_3_3) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_1)) (currstate s_2_0) (currstate s_3_3)  (increase (total-cost) 4))
) 

(:action del-b-ct9
:precondition (and (currstate t3) (currstate s_2_1) (currstate s_3_0) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_1)) (currstate s_2_0) (not (currstate s_3_0)) (currstate s_3_1)  (increase (total-cost) 1))
) 

(:action del-c-ct10
:precondition (and (currstate t2) (currstate s_2_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
) 

(:action del-d-ct11
:precondition (and (currstate t1) (currstate s_5_0) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_5_0)) (currstate s_5_1)  (increase (total-cost) 1))
) 

(:action del-d-ct12
:precondition (and (currstate t1) (currstate s_2_1) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
) 

(:action del-d-ct13
:precondition (and (currstate t1) (currstate s_5_1) (not (currstate s_2_1)) )
:effect (and (not (currstate t1)) (currstate t2) (currstate s_5_1)  (increase (total-cost) 4))
) 

(:action del-d-ct14
:precondition (and (currstate t1) (currstate s_5_0) (currstate s_2_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_5_0)) (currstate s_5_1) (not (currstate s_2_1)) (currstate s_2_0)  (increase (total-cost) 1))
) 

(:action del-d-ct15
:precondition (and (currstate t1) (currstate s_2_1) (currstate s_5_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_1)) (currstate s_2_0) (currstate s_5_1)  (increase (total-cost) 4))
) 

(:action del-a-t0t1
:precondition (and (currstate t0) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1)  (increase (total-cost) 1))
) 

(:action del-d-t1t2
:precondition (and (currstate t1) (not (currstate s_5_0)) (not (currstate s_2_1)) (not (currstate s_5_1)) )
:effect (and (not (currstate t1)) (currstate t2)  (increase (total-cost) 1))
) 

(:action del-c-t2t3
:precondition (and (currstate t2) (not (currstate s_2_1)) )
:effect (and (not (currstate t2)) (currstate t3)  (increase (total-cost) 1))
) 

(:action del-b-t3t4
:precondition (and (currstate t3) (not (currstate s_3_1)) (not (currstate s_2_1)) (not (currstate s_3_2)) (not (currstate s_3_3)) (not (currstate s_3_0)) )
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