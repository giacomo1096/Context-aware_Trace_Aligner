(define (domain Mining)
(:requirements :typing :equality)
(:types state)

(:predicates
(currstate ?s - state)
)

(:functions
(total-cost)
)

(:action add-b-ct0
:precondition (and (currstate s_0_3) (not (currstate s_1_1)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2)  (increase (total-cost) 1))
)

(:action add-b-ct1
:precondition (and (currstate s_0_0) (not (currstate s_1_1)) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-b-ct2
:precondition (and (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-b-ct3
:precondition (and (currstate s_0_3) (currstate s_1_1) )
:effect (and (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-b-ct4
:precondition (and (currstate s_0_0) (currstate s_1_1) )
:effect (and (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-c-ct5
:precondition (and (currstate s_0_3) (not (currstate s_3_1)) (not (currstate s_1_1)) (not (currstate s_3_0)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0)  (increase (total-cost) 1))
)

(:action add-c-ct6
:precondition (and (currstate s_0_2) (not (currstate s_3_1)) (not (currstate s_1_1)) (not (currstate s_3_0)) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1)  (increase (total-cost) 1))
)

(:action add-c-ct7
:precondition (and (currstate s_3_1) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (currstate s_3_1)  (increase (total-cost) 5))
)

(:action add-c-ct8
:precondition (and (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_3_1)) (not (currstate s_3_0)) )
:effect (and (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-c-ct9
:precondition (and (currstate s_3_0) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (currstate s_3_0)  (increase (total-cost) 3))
)

(:action add-c-ct10
:precondition (and (currstate s_0_3) (currstate s_3_1) (not (currstate s_1_1)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0) (currstate s_3_1)  (increase (total-cost) 5))
)

(:action add-c-ct11
:precondition (and (currstate s_0_3) (currstate s_1_1) (not (currstate s_3_1)) (not (currstate s_3_0)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-c-ct12
:precondition (and (currstate s_0_3) (currstate s_3_0) (not (currstate s_1_1)) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0) (currstate s_3_0)  (increase (total-cost) 3))
)

(:action add-c-ct13
:precondition (and (currstate s_0_2) (currstate s_3_1) (not (currstate s_1_1)) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1) (currstate s_3_1)  (increase (total-cost) 5))
)

(:action add-c-ct14
:precondition (and (currstate s_0_2) (currstate s_1_1) (not (currstate s_3_1)) (not (currstate s_3_0)) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action add-c-ct15
:precondition (and (currstate s_0_2) (currstate s_3_0) (not (currstate s_1_1)) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1) (currstate s_3_0)  (increase (total-cost) 3))
)

(:action add-c-ct16
:precondition (and (currstate s_3_1) (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 5))
)

(:action add-c-ct17
:precondition (and (currstate s_1_1) (currstate s_3_0) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate s_1_1)) (currstate s_1_0) (currstate s_3_0)  (increase (total-cost) 3))
)

(:action add-c-ct18
:precondition (and (currstate s_0_3) (currstate s_3_1) (currstate s_1_1) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 5))
)

(:action add-c-ct19
:precondition (and (currstate s_0_3) (currstate s_1_1) (currstate s_3_0) )
:effect (and (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) (currstate s_3_0)  (increase (total-cost) 3))
)

(:action add-c-ct20
:precondition (and (currstate s_0_2) (currstate s_3_1) (currstate s_1_1) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 5))
)

(:action add-c-ct21
:precondition (and (currstate s_0_2) (currstate s_1_1) (currstate s_3_0) )
:effect (and (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) (currstate s_3_0)  (increase (total-cost) 3))
)

(:action add-a-ct22
:precondition (currstate s_1_2) 
:effect (and (currstate s_1_2)  (increase (total-cost) 3))
)

(:action add-a-ct23
:precondition (currstate s_1_0) 
:effect (and (not (currstate s_1_0)) (currstate s_1_1)  (increase (total-cost) 1))
)

(:action add-a-ct24
:precondition (currstate s_1_1) 
:effect (and (not (currstate s_1_1)) (currstate s_1_2)  (increase (total-cost) 1))
)

(:action add-d-ct25
:precondition (currstate s_1_1) 
:effect (and (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
)

(:action sync-b-ct0
:precondition (and (currstate t2) (currstate s_0_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_3)) (currstate s_0_2) )
)

(:action sync-b-ct1
:precondition (and (currstate t2) (currstate s_0_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_0)) (currstate s_0_1) )
)

(:action sync-b-ct2
:precondition (and (currstate t2) (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct3
:precondition (and (currstate t2) (currstate s_0_3) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_3)) (currstate s_0_2) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-b-ct4
:precondition (and (currstate t2) (currstate s_0_0) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_0_0)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-c-ct5
:precondition (and (currstate t1) (currstate s_0_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) )
)

(:action sync-c-ct6
:precondition (and (currstate t1) (currstate s_0_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) )
)

(:action sync-c-ct7
:precondition (and (currstate t1) (currstate s_1_1) (not (currstate s_0_3)) (not (currstate s_0_2)) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-c-ct8
:precondition (and (currstate t1) (currstate s_0_3) (currstate s_1_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_3)) (currstate s_0_0) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-c-ct9
:precondition (and (currstate t1) (currstate s_0_2) (currstate s_1_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_0_2)) (currstate s_0_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct10
:precondition (and (currstate t0) (currstate s_2_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_0) )
)

(:action sync-a-ct11
:precondition (and (currstate t0) (currstate s_2_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_3)) (currstate s_2_0) )
)

(:action sync-a-ct12
:precondition (and (currstate t0) (currstate s_1_1) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct13
:precondition (and (currstate t0) (currstate s_2_1) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-a-ct14
:precondition (and (currstate t0) (currstate s_2_2) (currstate s_1_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_2)) (currstate s_2_0) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct15
:precondition (and (currstate t0) (currstate s_2_3) (currstate s_1_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_2_3)) (currstate s_2_0) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-ct16
:precondition (and (currstate t0) (currstate s_1_1) (currstate s_2_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_1)) (currstate s_2_0) )
)

(:action sync-d-ct17
:precondition (and (currstate t3) (currstate s_3_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_0)) (currstate s_3_1) )
)

(:action sync-d-ct18
:precondition (and (currstate t3) (currstate s_1_1) (not (currstate s_3_0)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-d-ct19
:precondition (and (currstate t3) (currstate s_3_0) (currstate s_1_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_3_0)) (currstate s_3_1) (not (currstate s_1_1)) (currstate s_1_0) )
)

(:action sync-a-t0t1
:precondition (and (currstate t0) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_1_1)) (not (currstate s_2_1)) )
:effect (and (not (currstate t0)) (currstate t1))) 

(:action sync-c-t1t2
:precondition (and (currstate t1) (not (currstate s_0_3)) (not (currstate s_0_2)) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2))) 

(:action sync-b-t2t3
:precondition (and (currstate t2) (not (currstate s_0_3)) (not (currstate s_0_0)) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3))) 

(:action sync-d-t3t4
:precondition (and (currstate t3) (not (currstate s_3_0)) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4))) 

(:action del-b-ct0
:precondition (and (currstate t2) (currstate s_2_1) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_1)) (currstate s_2_2)  (increase (total-cost) 3))
) 

(:action del-b-ct1
:precondition (and (currstate t2) (currstate s_2_2) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_3)  (increase (total-cost) 4))
) 

(:action del-b-ct2
:precondition (and (currstate t2) (currstate s_1_1) (not (currstate s_2_1)) (not (currstate s_2_2)) (not (currstate s_2_3)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-b-ct3
:precondition (and (currstate t2) (currstate s_2_3) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (currstate s_2_3)  (increase (total-cost) 4))
) 

(:action del-b-ct4
:precondition (and (currstate t2) (currstate s_2_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
) 

(:action del-b-ct5
:precondition (and (currstate t2) (currstate s_2_1) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_1)) (currstate s_2_2) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 3))
) 

(:action del-b-ct6
:precondition (and (currstate t2) (currstate s_2_2) (currstate s_1_1) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_2_2)) (currstate s_2_3) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 4))
) 

(:action del-b-ct7
:precondition (and (currstate t2) (currstate s_1_1) (currstate s_2_3) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0) (currstate s_2_3)  (increase (total-cost) 4))
) 

(:action del-b-ct8
:precondition (and (currstate t2) (currstate s_1_1) (currstate s_2_0) )
:effect (and (not (currstate t2)) (currstate t3) (not (currstate s_1_1)) (currstate s_1_0) (not (currstate s_2_0)) (currstate s_2_1)  (increase (total-cost) 1))
) 

(:action del-c-ct9
:precondition (and (currstate t1) (currstate s_1_1) )
:effect (and (not (currstate t1)) (currstate t2) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-a-ct10
:precondition (and (currstate t0) (currstate s_1_1) )
:effect (and (not (currstate t0)) (currstate t1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-d-ct11
:precondition (and (currstate t3) (currstate s_4_0) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_0)) (currstate s_4_1)  (increase (total-cost) 1))
) 

(:action del-d-ct12
:precondition (and (currstate t3) (currstate s_1_1) (not (currstate s_4_0)) (not (currstate s_4_1)) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-d-ct13
:precondition (and (currstate t3) (currstate s_4_1) (not (currstate s_1_1)) )
:effect (and (not (currstate t3)) (currstate t4) (currstate s_4_1)  (increase (total-cost) 4))
) 

(:action del-d-ct14
:precondition (and (currstate t3) (currstate s_4_0) (currstate s_1_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_4_0)) (currstate s_4_1) (not (currstate s_1_1)) (currstate s_1_0)  (increase (total-cost) 1))
) 

(:action del-d-ct15
:precondition (and (currstate t3) (currstate s_1_1) (currstate s_4_1) )
:effect (and (not (currstate t3)) (currstate t4) (not (currstate s_1_1)) (currstate s_1_0) (currstate s_4_1)  (increase (total-cost) 4))
) 

(:action del-a-t0t1
:precondition (and (currstate t0) (not (currstate s_1_1)) )
:effect (and (not (currstate t0)) (currstate t1)  (increase (total-cost) 1))
) 

(:action del-c-t1t2
:precondition (and (currstate t1) (not (currstate s_1_1)) )
:effect (and (not (currstate t1)) (currstate t2)  (increase (total-cost) 1))
) 

(:action del-b-t2t3
:precondition (and (currstate t2) (not (currstate s_2_1)) (not (currstate s_2_2)) (not (currstate s_1_1)) (not (currstate s_2_3)) (not (currstate s_2_0)) )
:effect (and (not (currstate t2)) (currstate t3)  (increase (total-cost) 1))
) 

(:action del-d-t3t4
:precondition (and (currstate t3) (not (currstate s_4_0)) (not (currstate s_1_1)) (not (currstate s_4_1)) )
:effect (and (not (currstate t3)) (currstate t4)  (increase (total-cost) 1))
) 

(:action goto-abstract_states-cs0
:precondition (and (currstate t4) (currstate s_0_2) )
:effect (and (currstate s_0_abstract) (not (currstate s_0_2)) )
)

(:action goto-abstract_states-cs1
:precondition (and (currstate t4) (currstate s_0_0) )
:effect (and (currstate s_0_abstract) (not (currstate s_0_0)) )
)

)