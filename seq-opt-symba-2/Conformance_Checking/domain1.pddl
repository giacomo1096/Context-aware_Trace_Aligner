(define (domain Mining)
(:requirements :typing :equality)
(:types state)

(:predicates
(currstate ?s - state)
)

(:functions
(total-cost)
)

(:action add-c-ct0
:precondition (and (currstate s_2_2) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0)  (increase (total-cost) 1))
)

(:action add-c-ct1
:precondition (and (currstate s_0_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0)  (increase (total-cost) 1))
)

(:action add-c-ct2
:precondition (and (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-c-ct3
:precondition (and (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-c-ct4
:precondition (and (currstate s_2_0) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-c-ct5
:precondition (and (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct6
:precondition (and (currstate s_5_1) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct7
:precondition (and (currstate s_2_2) (currstate s_0_1) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0)  (increase (total-cost) 1))
)

(:action add-c-ct8
:precondition (and (currstate s_2_2) (currstate s_3_2) (not (currstate s_0_1)) (not (currstate s_4_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-c-ct9
:precondition (and (currstate s_2_2) (currstate s_4_2) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-c-ct10
:precondition (and (currstate s_2_2) (currstate s_5_0) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct11
:precondition (and (currstate s_2_2) (currstate s_5_1) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct12
:precondition (and (currstate s_0_1) (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-c-ct13
:precondition (and (currstate s_0_1) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-c-ct14
:precondition (and (currstate s_0_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-c-ct15
:precondition (and (currstate s_0_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct16
:precondition (and (currstate s_0_1) (currstate s_5_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct17
:precondition (and (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-c-ct18
:precondition (and (currstate s_3_2) (currstate s_2_0) (not (currstate s_0_1)) (not (currstate s_4_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-c-ct19
:precondition (and (currstate s_3_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct20
:precondition (and (currstate s_3_2) (currstate s_5_1) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct21
:precondition (and (currstate s_4_2) (currstate s_2_0) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-c-ct22
:precondition (and (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct23
:precondition (and (currstate s_4_2) (currstate s_5_1) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct24
:precondition (and (currstate s_2_0) (currstate s_5_0) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct25
:precondition (and (currstate s_2_0) (currstate s_5_1) (not (currstate s_0_1)) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct26
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_3_2) (not (currstate s_4_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-c-ct27
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-c-ct28
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct29
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_5_1) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct30
:precondition (and (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (not (currstate s_0_1)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-c-ct31
:precondition (and (currstate s_2_2) (currstate s_3_2) (currstate s_5_0) (not (currstate s_0_1)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_3_2)) (currstate s_3_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct32
:precondition (and (currstate s_2_2) (currstate s_3_2) (currstate s_5_1) (not (currstate s_0_1)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_3_2)) (currstate s_3_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct33
:precondition (and (currstate s_2_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_0_1)) (not (currstate s_3_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct34
:precondition (and (currstate s_2_2) (currstate s_4_2) (currstate s_5_1) (not (currstate s_0_1)) (not (currstate s_3_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct35
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-c-ct36
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-c-ct37
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct38
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_5_1) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct39
:precondition (and (currstate s_0_1) (currstate s_4_2) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-c-ct40
:precondition (and (currstate s_0_1) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct41
:precondition (and (currstate s_0_1) (currstate s_4_2) (currstate s_5_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct42
:precondition (and (currstate s_0_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct43
:precondition (and (currstate s_0_1) (currstate s_2_0) (currstate s_5_1) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct44
:precondition (and (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_0_1)) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-c-ct45
:precondition (and (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct46
:precondition (and (currstate s_3_2) (currstate s_4_2) (currstate s_5_1) (not (currstate s_2_2)) (not (currstate s_0_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct47
:precondition (and (currstate s_3_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_0_1)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct48
:precondition (and (currstate s_3_2) (currstate s_2_0) (currstate s_5_1) (not (currstate s_0_1)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct49
:precondition (and (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_0_1)) (not (currstate s_3_2)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct50
:precondition (and (currstate s_4_2) (currstate s_2_0) (currstate s_5_1) (not (currstate s_0_1)) (not (currstate s_3_2)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct51
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_3_2) (currstate s_4_2) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-c-ct52
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_3_2) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct53
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_3_2) (currstate s_5_1) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct54
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_4_2) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct55
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_4_2) (currstate s_5_1) (not (currstate s_3_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct56
:precondition (and (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_0_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct57
:precondition (and (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_5_1) (not (currstate s_0_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct58
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_5_0)) (not (currstate s_5_1)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-c-ct59
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct60
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_4_2) (currstate s_5_1) (not (currstate s_2_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct61
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct62
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_2_0) (currstate s_5_1) (not (currstate s_4_2)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct63
:precondition (and (currstate s_0_1) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct64
:precondition (and (currstate s_0_1) (currstate s_4_2) (currstate s_2_0) (currstate s_5_1) (not (currstate s_3_2)) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct65
:precondition (and (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_0_1)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct66
:precondition (and (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_1) (not (currstate s_0_1)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct67
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct68
:precondition (and (currstate s_2_2) (currstate s_0_1) (currstate s_3_2) (currstate s_4_2) (currstate s_5_1) )
:effect (and (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-c-ct69
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_0)  (increase (total-cost) 3))
)

(:action add-c-ct70
:precondition (and (currstate s_0_1) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_1) )
:effect (and (not (currstate s_0_1)) (currstate s_0_0) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_1) (currstate s_5_1)  (increase (total-cost) 5))
)

(:action add-a-ct71
:precondition (and (currstate s_2_2) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-a-ct72
:precondition (and (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-a-ct73
:precondition (and (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-a-ct74
:precondition (and (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-a-ct75
:precondition (and (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate s_2_0)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct76
:precondition (and (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-a-ct77
:precondition (and (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-a-ct78
:precondition (and (currstate s_2_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-a-ct79
:precondition (and (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-a-ct80
:precondition (and (currstate s_3_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-a-ct81
:precondition (and (currstate s_3_2) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct82
:precondition (and (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-a-ct83
:precondition (and (currstate s_4_2) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct84
:precondition (and (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct85
:precondition (and (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (not (currstate s_1_1)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-a-ct86
:precondition (and (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-a-ct87
:precondition (and (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_3_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-a-ct88
:precondition (and (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-a-ct89
:precondition (and (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_1_1)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct90
:precondition (and (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_4_2)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct91
:precondition (and (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-a-ct92
:precondition (and (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-a-ct93
:precondition (and (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2)  (increase (total-cost) 1))
)

(:action add-b-ct94
:precondition (and (currstate s_2_2) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_3_0)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-b-ct95
:precondition (and (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-b-ct96
:precondition (and (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_3_0)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-b-ct97
:precondition (and (currstate s_3_0) (not (currstate s_2_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_3_0)) (currstate s_3_2)  (increase (total-cost) 1))
)

(:action add-b-ct98
:precondition (and (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-b-ct99
:precondition (and (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_3_0)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-b-ct100
:precondition (and (currstate s_2_2) (currstate s_3_0) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_0)) (currstate s_3_2)  (increase (total-cost) 1))
)

(:action add-b-ct101
:precondition (and (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-b-ct102
:precondition (and (currstate s_4_2) (currstate s_3_0) (not (currstate s_2_2)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_3_0)) (currstate s_3_2)  (increase (total-cost) 1))
)

(:action add-b-ct103
:precondition (and (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-b-ct104
:precondition (and (currstate s_2_2) (currstate s_4_2) (currstate s_3_0) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_3_0)) (currstate s_3_2)  (increase (total-cost) 1))
)

(:action add-d-ct105
:precondition (and (currstate s_3_0) (not (currstate s_4_0)) (not (currstate s_2_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_3_0)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-d-ct106
:precondition (and (currstate s_4_0) (not (currstate s_3_0)) (not (currstate s_3_2)) (not (currstate s_2_2)) )
:effect (and (not (currstate s_4_0)) (currstate s_4_2)  (increase (total-cost) 1))
)

(:action add-d-ct107
:precondition (and (currstate s_3_2) (not (currstate s_4_0)) (not (currstate s_2_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_0)  (increase (total-cost) 1))
)

(:action add-d-ct108
:precondition (and (currstate s_2_2) (not (currstate s_3_0)) (not (currstate s_4_0)) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-d-ct109
:precondition (and (currstate s_4_2) (not (currstate s_3_0)) (not (currstate s_3_2)) (not (currstate s_2_2)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-d-ct110
:precondition (and (currstate s_3_0) (currstate s_4_0) (not (currstate s_2_2)) )
:effect (and (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_4_0)) (currstate s_4_2)  (increase (total-cost) 1))
)

(:action add-d-ct111
:precondition (and (currstate s_3_0) (currstate s_2_2) (not (currstate s_4_0)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-d-ct112
:precondition (and (currstate s_3_0) (currstate s_4_2) (not (currstate s_2_2)) )
:effect (and (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-d-ct113
:precondition (and (currstate s_4_0) (currstate s_3_2) (not (currstate s_2_2)) )
:effect (and (not (currstate s_4_0)) (currstate s_4_2) (not (currstate s_3_2)) (currstate s_3_0)  (increase (total-cost) 1))
)

(:action add-d-ct114
:precondition (and (currstate s_4_0) (currstate s_2_2) (not (currstate s_3_0)) (not (currstate s_3_2)) )
:effect (and (not (currstate s_4_0)) (currstate s_4_2) (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-d-ct115
:precondition (and (currstate s_3_2) (currstate s_2_2) (not (currstate s_4_0)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-d-ct116
:precondition (and (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-d-ct117
:precondition (and (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_0)) (not (currstate s_3_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-d-ct118
:precondition (and (currstate s_3_0) (currstate s_4_0) (currstate s_2_2) )
:effect (and (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_4_0)) (currstate s_4_2) (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-d-ct119
:precondition (and (currstate s_3_0) (currstate s_2_2) (currstate s_4_2) )
:effect (and (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-d-ct120
:precondition (and (currstate s_4_0) (currstate s_3_2) (currstate s_2_2) )
:effect (and (not (currstate s_4_0)) (currstate s_4_2) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-d-ct121
:precondition (and (currstate s_3_2) (currstate s_2_2) (currstate s_4_2) )
:effect (and (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-e-ct122
:precondition (and (currstate s_4_0) (not (currstate s_2_2)) (not (currstate s_3_2)) )
:effect (and (not (currstate s_4_0)) (currstate s_4_1)  (increase (total-cost) 1))
)

(:action add-e-ct123
:precondition (and (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_0)  (increase (total-cost) 1))
)

(:action add-e-ct124
:precondition (and (currstate s_2_2) (not (currstate s_4_0)) (not (currstate s_4_2)) (not (currstate s_3_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-e-ct125
:precondition (and (currstate s_3_2) (not (currstate s_4_0)) (not (currstate s_4_2)) (not (currstate s_2_2)) )
:effect (and (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-e-ct126
:precondition (and (currstate s_4_0) (currstate s_2_2) (not (currstate s_3_2)) )
:effect (and (not (currstate s_4_0)) (currstate s_4_1) (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-e-ct127
:precondition (and (currstate s_4_0) (currstate s_3_2) (not (currstate s_2_2)) )
:effect (and (not (currstate s_4_0)) (currstate s_4_1) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-e-ct128
:precondition (and (currstate s_4_2) (currstate s_2_2) (not (currstate s_3_2)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_0) (not (currstate s_2_2)) (currstate s_2_1)  (increase (total-cost) 1))
)

(:action add-e-ct129
:precondition (and (currstate s_4_2) (currstate s_3_2) (not (currstate s_2_2)) )
:effect (and (not (currstate s_4_2)) (currstate s_4_0) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-e-ct130
:precondition (and (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_0)) (not (currstate s_4_2)) )
:effect (and (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-e-ct131
:precondition (and (currstate s_4_0) (currstate s_2_2) (currstate s_3_2) )
:effect (and (not (currstate s_4_0)) (currstate s_4_1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action add-e-ct132
:precondition (and (currstate s_4_2) (currstate s_2_2) (currstate s_3_2) )
:effect (and (not (currstate s_4_2)) (currstate s_4_0) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1)  (increase (total-cost) 1))
)

(:action sync-a-ct23
:precondition (and (currstate t0) (currstate s_2_2) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-a-ct23
:precondition (and (currstate t1) (currstate s_2_2) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-a-ct23
:precondition (and (currstate t2) (currstate s_2_2) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-a-ct23
:precondition (and (currstate t3) (currstate s_2_2) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-a-ct23
:precondition (and (currstate t4) (currstate s_2_2) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-a-ct23
:precondition (and (currstate t5) (currstate s_2_2) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-a-ct24
:precondition (and (currstate t0) (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct24
:precondition (and (currstate t1) (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct24
:precondition (and (currstate t2) (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct24
:precondition (and (currstate t3) (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct24
:precondition (and (currstate t4) (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct24
:precondition (and (currstate t5) (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct25
:precondition (and (currstate t0) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct25
:precondition (and (currstate t1) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct25
:precondition (and (currstate t2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct25
:precondition (and (currstate t3) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct25
:precondition (and (currstate t4) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct25
:precondition (and (currstate t5) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct26
:precondition (and (currstate t0) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct26
:precondition (and (currstate t1) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct26
:precondition (and (currstate t2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct26
:precondition (and (currstate t3) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct26
:precondition (and (currstate t4) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct26
:precondition (and (currstate t5) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct27
:precondition (and (currstate t0) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct27
:precondition (and (currstate t1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct27
:precondition (and (currstate t2) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct27
:precondition (and (currstate t3) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct27
:precondition (and (currstate t4) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct27
:precondition (and (currstate t5) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct28
:precondition (and (currstate t0) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct28
:precondition (and (currstate t1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct28
:precondition (and (currstate t2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct28
:precondition (and (currstate t3) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct28
:precondition (and (currstate t4) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct28
:precondition (and (currstate t5) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct29
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct29
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct29
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct29
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct29
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct29
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-ct30
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct30
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct30
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct30
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct30
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct30
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct31
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct31
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct31
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct31
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct31
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct31
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct32
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct32
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct32
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct32
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct32
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct32
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct33
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct33
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct33
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct33
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct33
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct33
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct34
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct34
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct34
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct34
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct34
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct34
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct35
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct35
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct35
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct35
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct35
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct35
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct36
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct36
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct36
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct36
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct36
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct36
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct37
:precondition (and (currstate t0) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct37
:precondition (and (currstate t1) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct37
:precondition (and (currstate t2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct37
:precondition (and (currstate t3) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct37
:precondition (and (currstate t4) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct37
:precondition (and (currstate t5) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct38
:precondition (and (currstate t0) (currstate s_4_2) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct38
:precondition (and (currstate t1) (currstate s_4_2) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct38
:precondition (and (currstate t2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct38
:precondition (and (currstate t3) (currstate s_4_2) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct38
:precondition (and (currstate t4) (currstate s_4_2) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct38
:precondition (and (currstate t5) (currstate s_4_2) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct39
:precondition (and (currstate t0) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct39
:precondition (and (currstate t1) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct39
:precondition (and (currstate t2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct39
:precondition (and (currstate t3) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct39
:precondition (and (currstate t4) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct39
:precondition (and (currstate t5) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct40
:precondition (and (currstate t0) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct40
:precondition (and (currstate t1) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct40
:precondition (and (currstate t2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct40
:precondition (and (currstate t3) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct40
:precondition (and (currstate t4) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct40
:precondition (and (currstate t5) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct41
:precondition (and (currstate t0) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct41
:precondition (and (currstate t1) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct41
:precondition (and (currstate t2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct41
:precondition (and (currstate t3) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct41
:precondition (and (currstate t4) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct41
:precondition (and (currstate t5) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct42
:precondition (and (currstate t0) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct42
:precondition (and (currstate t1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct42
:precondition (and (currstate t2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct42
:precondition (and (currstate t3) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct42
:precondition (and (currstate t4) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct42
:precondition (and (currstate t5) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct43
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct43
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct43
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct43
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct43
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct43
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-a-ct44
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct44
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct44
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct44
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct44
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct44
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct45
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_3_2) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct45
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_3_2) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct45
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_3_2) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct45
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_3_2) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct45
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_3_2) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct45
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_3_2) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct46
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct46
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct46
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct46
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct46
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct46
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct47
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct47
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct47
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct47
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct47
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct47
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct48
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct48
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct48
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct48
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct48
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct48
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct49
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct49
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct49
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct49
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct49
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct49
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct50
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct50
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct50
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct50
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct50
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct50
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (not (currstate s_1_1)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct51
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct51
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct51
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct51
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct51
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct51
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct52
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct52
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct52
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct52
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct52
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct52
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_4_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct53
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct53
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct53
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct53
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct53
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct53
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_4_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct54
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct54
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct54
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct54
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct54
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct54
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct55
:precondition (and (currstate t0) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct55
:precondition (and (currstate t1) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct55
:precondition (and (currstate t2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct55
:precondition (and (currstate t3) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct55
:precondition (and (currstate t4) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct55
:precondition (and (currstate t5) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_3_2)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct56
:precondition (and (currstate t0) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct56
:precondition (and (currstate t1) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct56
:precondition (and (currstate t2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct56
:precondition (and (currstate t3) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct56
:precondition (and (currstate t4) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct56
:precondition (and (currstate t5) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct57
:precondition (and (currstate t0) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct57
:precondition (and (currstate t1) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct57
:precondition (and (currstate t2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct57
:precondition (and (currstate t3) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct57
:precondition (and (currstate t4) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct57
:precondition (and (currstate t5) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct58
:precondition (and (currstate t0) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct58
:precondition (and (currstate t1) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct58
:precondition (and (currstate t2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct58
:precondition (and (currstate t3) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct58
:precondition (and (currstate t4) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct58
:precondition (and (currstate t5) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct59
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct59
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct59
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct59
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct59
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct59
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct60
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct60
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct60
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct60
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct60
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct60
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct61
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct61
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct61
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct61
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct61
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct61
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_3_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct62
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct62
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct62
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct62
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct62
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct62
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct63
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct63
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct63
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct63
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct63
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct63
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) )
)

(:action sync-a-ct64
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct64
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct64
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct64
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct64
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct64
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) (not (currstate s_2_2)) (not (currstate s_2_0)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct65
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct65
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct65
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct65
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct65
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct65
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_4_2) (currstate s_2_0) (currstate s_5_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct66
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct66
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct66
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct66
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct66
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct66
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct67
:precondition (and (currstate t0) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct67
:precondition (and (currstate t1) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct67
:precondition (and (currstate t2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct67
:precondition (and (currstate t3) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct67
:precondition (and (currstate t4) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct67
:precondition (and (currstate t5) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) (not (currstate s_3_2)) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct68
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct68
:precondition (and (currstate t1) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct68
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct68
:precondition (and (currstate t3) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct68
:precondition (and (currstate t4) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct68
:precondition (and (currstate t5) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_5_0) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct69
:precondition (and (currstate t0) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct69
:precondition (and (currstate t1) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct69
:precondition (and (currstate t2) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct69
:precondition (and (currstate t3) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct69
:precondition (and (currstate t4) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-a-ct69
:precondition (and (currstate t5) (currstate s_3_2) (currstate s_4_2) (currstate s_1_1) (currstate s_2_0) (currstate s_5_0) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_2) (not (currstate s_5_0)) (currstate s_5_1) )
)

(:action sync-b-ct70
:precondition (and (currstate t6) (currstate s_2_2) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_3_0)) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-b-ct71
:precondition (and (currstate t6) (currstate s_3_2) (not (currstate s_2_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-b-ct72
:precondition (and (currstate t6) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_3_0)) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-b-ct73
:precondition (and (currstate t6) (currstate s_3_0) (not (currstate s_2_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_3_0)) (currstate s_3_2) )
)

(:action sync-b-ct74
:precondition (and (currstate t6) (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_2)) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-b-ct75
:precondition (and (currstate t6) (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_2)) (not (currstate s_3_0)) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-b-ct76
:precondition (and (currstate t6) (currstate s_2_2) (currstate s_3_0) (not (currstate s_4_2)) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_0)) (currstate s_3_2) )
)

(:action sync-b-ct77
:precondition (and (currstate t6) (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-b-ct78
:precondition (and (currstate t6) (currstate s_4_2) (currstate s_3_0) (not (currstate s_2_2)) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_3_0)) (currstate s_3_2) )
)

(:action sync-b-ct79
:precondition (and (currstate t6) (currstate s_2_2) (currstate s_3_2) (currstate s_4_2) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-b-ct80
:precondition (and (currstate t6) (currstate s_2_2) (currstate s_4_2) (currstate s_3_0) )
:effect (and (not (currstate t6)) (currstate t7) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) (not (currstate s_3_0)) (currstate s_3_2) )
)

(:action sync-d-ct81
:precondition (and (currstate t7) (currstate s_3_0) (not (currstate s_4_0)) (not (currstate s_2_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_0)) (currstate s_3_1) )
)

(:action sync-d-ct82
:precondition (and (currstate t7) (currstate s_4_0) (not (currstate s_3_0)) (not (currstate s_3_2)) (not (currstate s_2_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_4_0)) (currstate s_4_2) )
)

(:action sync-d-ct83
:precondition (and (currstate t7) (currstate s_3_2) (not (currstate s_4_0)) (not (currstate s_2_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_2)) (currstate s_3_0) )
)

(:action sync-d-ct84
:precondition (and (currstate t7) (currstate s_2_2) (not (currstate s_3_0)) (not (currstate s_4_0)) (not (currstate s_3_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-d-ct85
:precondition (and (currstate t7) (currstate s_4_2) (not (currstate s_3_0)) (not (currstate s_3_2)) (not (currstate s_2_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-d-ct86
:precondition (and (currstate t7) (currstate s_3_0) (currstate s_4_0) (not (currstate s_2_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_4_0)) (currstate s_4_2) )
)

(:action sync-d-ct87
:precondition (and (currstate t7) (currstate s_3_0) (currstate s_2_2) (not (currstate s_4_0)) (not (currstate s_4_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-d-ct88
:precondition (and (currstate t7) (currstate s_3_0) (currstate s_4_2) (not (currstate s_2_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-d-ct89
:precondition (and (currstate t7) (currstate s_4_0) (currstate s_3_2) (not (currstate s_2_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_4_0)) (currstate s_4_2) (not (currstate s_3_2)) (currstate s_3_0) )
)

(:action sync-d-ct90
:precondition (and (currstate t7) (currstate s_4_0) (currstate s_2_2) (not (currstate s_3_0)) (not (currstate s_3_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_4_0)) (currstate s_4_2) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-d-ct91
:precondition (and (currstate t7) (currstate s_3_2) (currstate s_2_2) (not (currstate s_4_0)) (not (currstate s_4_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-d-ct92
:precondition (and (currstate t7) (currstate s_3_2) (currstate s_4_2) (not (currstate s_2_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-d-ct93
:precondition (and (currstate t7) (currstate s_2_2) (currstate s_4_2) (not (currstate s_3_0)) (not (currstate s_3_2)) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-d-ct94
:precondition (and (currstate t7) (currstate s_3_0) (currstate s_4_0) (currstate s_2_2) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_4_0)) (currstate s_4_2) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-d-ct95
:precondition (and (currstate t7) (currstate s_3_0) (currstate s_2_2) (currstate s_4_2) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-d-ct96
:precondition (and (currstate t7) (currstate s_4_0) (currstate s_3_2) (currstate s_2_2) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_4_0)) (currstate s_4_2) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-d-ct97
:precondition (and (currstate t7) (currstate s_3_2) (currstate s_2_2) (currstate s_4_2) )
:effect (and (not (currstate t7)) (currstate t8) (not (currstate s_3_2)) (currstate s_3_0) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_4_2)) (currstate s_4_1) )
)

(:action sync-e-ct98
:precondition (and (currstate t8) (currstate s_4_0) (not (currstate s_2_2)) (not (currstate s_3_2)) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_4_0)) (currstate s_4_1) )
)

(:action sync-e-ct99
:precondition (and (currstate t8) (currstate s_4_2) (not (currstate s_2_2)) (not (currstate s_3_2)) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_4_2)) (currstate s_4_0) )
)

(:action sync-e-ct100
:precondition (and (currstate t8) (currstate s_2_2) (not (currstate s_4_0)) (not (currstate s_4_2)) (not (currstate s_3_2)) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-e-ct101
:precondition (and (currstate t8) (currstate s_3_2) (not (currstate s_4_0)) (not (currstate s_4_2)) (not (currstate s_2_2)) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-e-ct102
:precondition (and (currstate t8) (currstate s_4_0) (currstate s_2_2) (not (currstate s_3_2)) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_4_0)) (currstate s_4_1) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-e-ct103
:precondition (and (currstate t8) (currstate s_4_0) (currstate s_3_2) (not (currstate s_2_2)) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_4_0)) (currstate s_4_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-e-ct104
:precondition (and (currstate t8) (currstate s_4_2) (currstate s_2_2) (not (currstate s_3_2)) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_4_2)) (currstate s_4_0) (not (currstate s_2_2)) (currstate s_2_1) )
)

(:action sync-e-ct105
:precondition (and (currstate t8) (currstate s_4_2) (currstate s_3_2) (not (currstate s_2_2)) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_4_2)) (currstate s_4_0) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-e-ct106
:precondition (and (currstate t8) (currstate s_2_2) (currstate s_3_2) (not (currstate s_4_0)) (not (currstate s_4_2)) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-e-ct107
:precondition (and (currstate t8) (currstate s_4_0) (currstate s_2_2) (currstate s_3_2) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_4_0)) (currstate s_4_1) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-e-ct108
:precondition (and (currstate t8) (currstate s_4_2) (currstate s_2_2) (currstate s_3_2) )
:effect (and (not (currstate t8)) (currstate t9) (not (currstate s_4_2)) (currstate s_4_0) (not (currstate s_2_2)) (currstate s_2_1) (not (currstate s_3_2)) (currstate s_3_1) )
)

(:action sync-a-t0t1
:precondition (and (currstate t0) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t0)) (currstate t1))) 

(:action sync-a-t1t2
:precondition (and (currstate t1) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t1)) (currstate t2))) 

(:action sync-a-t2t3
:precondition (and (currstate t2) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t2)) (currstate t3))) 

(:action sync-a-t3t4
:precondition (and (currstate t3) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t3)) (currstate t4))) 

(:action sync-a-t4t5
:precondition (and (currstate t4) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t4)) (currstate t5))) 

(:action sync-a-t5t6
:precondition (and (currstate t5) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_1_1)) (not (currstate s_2_0)) (not (currstate s_5_0)) )
:effect (and (not (currstate t5)) (currstate t6))) 

(:action sync-b-t6t7
:precondition (and (currstate t6) (not (currstate s_2_2)) (not (currstate s_3_2)) (not (currstate s_4_2)) (not (currstate s_3_0)) )
:effect (and (not (currstate t6)) (currstate t7))) 

(:action sync-d-t7t8
:precondition (and (currstate t7) (not (currstate s_3_0)) (not (currstate s_4_0)) (not (currstate s_3_2)) (not (currstate s_2_2)) (not (currstate s_4_2)) )
:effect (and (not (currstate t7)) (currstate t8))) 

(:action sync-e-t8t9
:precondition (and (currstate t8) (not (currstate s_4_0)) (not (currstate s_4_2)) (not (currstate s_2_2)) (not (currstate s_3_2)) )
:effect (and (not (currstate t8)) (currstate t9))) 

(:action del-a-ct0
:precondition (and (currstate t0) (currstate s_6_0) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_6_0)) (currstate s_6_1)  (increase (total-cost) 1))
) 

(:action del-a-ct0
:precondition (and (currstate t1) (currstate s_6_0) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_6_0)) (currstate s_6_1)  (increase (total-cost) 1))
) 

(:action del-a-ct0
:precondition (and (currstate t2) (currstate s_6_0) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_6_0)) (currstate s_6_1)  (increase (total-cost) 1))
) 

(:action del-a-ct0
:precondition (and (currstate t3) (currstate s_6_0) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_6_0)) (currstate s_6_1)  (increase (total-cost) 1))
) 

(:action del-a-ct0
:precondition (and (currstate t4) (currstate s_6_0) )
:effect (and (not (currstate t4)) (currstate t5) (not (currstate s_6_0)) (currstate s_6_1)  (increase (total-cost) 1))
) 

(:action del-a-ct0
:precondition (and (currstate t5) (currstate s_6_0) )
:effect (and (not (currstate t5)) (currstate t6) (not (currstate s_6_0)) (currstate s_6_1)  (increase (total-cost) 1))
) 

(:action del-a-ct1
:precondition (and (currstate t0) (currstate s_6_1) )
:effect (and (not (currstate t0)) (currstate t1) (currstate s_6_1)  (increase (total-cost) 6))
) 

(:action del-a-ct1
:precondition (and (currstate t1) (currstate s_6_1) )
:effect (and (not (currstate t1)) (currstate t2) (currstate s_6_1)  (increase (total-cost) 6))
) 

(:action del-a-ct1
:precondition (and (currstate t2) (currstate s_6_1) )
:effect (and (not (currstate t2)) (currstate t3) (currstate s_6_1)  (increase (total-cost) 6))
) 

(:action del-a-ct1
:precondition (and (currstate t3) (currstate s_6_1) )
:effect (and (not (currstate t3)) (currstate t4) (currstate s_6_1)  (increase (total-cost) 6))
) 

(:action del-a-ct1
:precondition (and (currstate t4) (currstate s_6_1) )
:effect (and (not (currstate t4)) (currstate t5) (currstate s_6_1)  (increase (total-cost) 6))
) 

(:action del-a-ct1
:precondition (and (currstate t5) (currstate s_6_1) )
:effect (and (not (currstate t5)) (currstate t6) (currstate s_6_1)  (increase (total-cost) 6))
) 

(:action del-a-t0t1
:precondition (and (currstate t0) (not (currstate s_6_0)) (not (currstate s_6_1)) )
:effect (and (not (currstate t0)) (currstate t1)  (increase (total-cost) 1))
) 

(:action del-a-t1t2
:precondition (and (currstate t1) (not (currstate s_6_0)) (not (currstate s_6_1)) )
:effect (and (not (currstate t1)) (currstate t2)  (increase (total-cost) 1))
) 

(:action del-a-t2t3
:precondition (and (currstate t2) (not (currstate s_6_0)) (not (currstate s_6_1)) )
:effect (and (not (currstate t2)) (currstate t3)  (increase (total-cost) 1))
) 

(:action del-a-t3t4
:precondition (and (currstate t3) (not (currstate s_6_0)) (not (currstate s_6_1)) )
:effect (and (not (currstate t3)) (currstate t4)  (increase (total-cost) 1))
) 

(:action del-a-t4t5
:precondition (and (currstate t4) (not (currstate s_6_0)) (not (currstate s_6_1)) )
:effect (and (not (currstate t4)) (currstate t5)  (increase (total-cost) 1))
) 

(:action del-a-t5t6
:precondition (and (currstate t5) (not (currstate s_6_0)) (not (currstate s_6_1)) )
:effect (and (not (currstate t5)) (currstate t6)  (increase (total-cost) 1))
) 

(:action del-b-t6t7
:precondition (currstate t6) 
:effect (and (not (currstate t6)) (currstate t7)  (increase (total-cost) 1))
) 

(:action del-d-t7t8
:precondition (currstate t7) 
:effect (and (not (currstate t7)) (currstate t8)  (increase (total-cost) 1))
) 

(:action del-e-t8t9
:precondition (currstate t8) 
:effect (and (not (currstate t8)) (currstate t9)  (increase (total-cost) 1))
) 

)