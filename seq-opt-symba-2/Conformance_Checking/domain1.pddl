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
:precondition (and (currstate s_0_3) (not (currstate s_1_0)) (not (currstate s_1_1)) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2)  (increase (total-cost) 1))
)

(:action add-a-ct1
:precondition (and (currstate s_0_0) (not (currstate s_1_0)) (not (currstate s_1_1)) (not (currstate s_1_2)) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-a-ct2
:precondition (and (currstate s_1_0) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate s_1_0)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-a-ct3
:precondition (and (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate s_1_1)) (currstate s_1_2)  (increase (total-cost) 1))
)

(:action add-a-ct4
:precondition (and (currstate s_1_2) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (currstate s_1_2)  (increase (total-cost) 3))
)

(:action add-a-ct5
:precondition (and (currstate s_0_3) (currstate s_1_0) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_0)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-a-ct6
:precondition (and (currstate s_0_3) (currstate s_1_1) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_1)) (currstate s_1_2)  (increase (total-cost) 1))
)

(:action add-a-ct7
:precondition (and (currstate s_0_3) (currstate s_1_2) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (currstate s_1_2)  (increase (total-cost) 3))
)

(:action add-a-ct8
:precondition (and (currstate s_0_0) (currstate s_1_0) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_1_0)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-a-ct9
:precondition (and (currstate s_0_0) (currstate s_1_1) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_2)  (increase (total-cost) 1))
)

(:action add-a-ct10
:precondition (and (currstate s_0_0) (currstate s_1_2) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1) (currstate s_1_2)  (increase (total-cost) 3))
)

(:action add-b-ct11
:precondition (and (currstate s_0_3) (not (currstate s_1_1)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0)  (increase (total-cost) 1))
)

(:action add-b-ct12
:precondition (and (currstate s_0_2) (not (currstate s_1_1)) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-b-ct13
:precondition (and (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-b-ct14
:precondition (and (currstate s_0_3) (currstate s_1_1) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-b-ct15
:precondition (and (currstate s_0_2) (currstate s_1_1) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-c-ct16
:precondition (currstate s_1_1) 
:effect (and (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action sync-a-ct0
:precondition (and (currstate t1) (currstate s_0_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-a-ct1
:precondition (and (currstate t1) (currstate s_0_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-a-ct2
:precondition (and (currstate t1) (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct3
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_1_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct4
:precondition (and (currstate t1) (currstate s_0_0) (currstate s_1_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct5
:precondition (and (currstate t0) (currstate s_0_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-b-ct6
:precondition (and (currstate t0) (currstate s_0_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-b-ct7
:precondition (and (currstate t0) (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct8
:precondition (and (currstate t0) (currstate s_0_3) (currstate s_1_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct9
:precondition (and (currstate t0) (currstate s_0_2) (currstate s_1_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-c-ct10
:precondition (and (currstate t2) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-t0t1
:precondition (and (currstate t0) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1))) 

(:action sync-a-t1t2
:precondition (and (currstate t1) (not (currstate s_0_3)) (not (currstate s_0_0)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2))) 

(:action sync-c-t2t3
:precondition (and (currstate t2) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3))) 

(:action del-a-ct0
:precondition (and (currstate t1) (currstate s_1_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-b-ct1
:precondition (and (currstate t0) (currstate s_1_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-c-ct2
:precondition (and (currstate t2) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-b-t0t1
:precondition (and (currstate t0) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1)  (increase (total-cost) 1))
) 

(:action del-a-t1t2
:precondition (and (currstate t1) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2)  (increase (total-cost) 1))
) 

(:action del-c-t2t3
:precondition (and (currstate t2) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3)  (increase (total-cost) 1))
) 

(:action goto-abstract_states-cs0
:precondition (and (currstate t3) (currstate s_0_2) )
:effect (and (currstate s_0_abstract) (not (currstate s_0_2)) )
)

(:action goto-abstract_states-cs1
:precondition (and (currstate t3) (currstate s_0_0) )
:effect (and (currstate s_0_abstract) (not (currstate s_0_0)) )
)

)