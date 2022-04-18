begin_version
3
end_version
begin_metric
1
end_metric
7
begin_variable
var0
-1
2
Atom currstate(s_0_0)
NegatedAtom currstate(s_0_0)
end_variable
begin_variable
var1
-1
2
Atom currstate(s_0_1)
NegatedAtom currstate(s_0_1)
end_variable
begin_variable
var2
-1
2
Atom currstate(s_1_0)
NegatedAtom currstate(s_1_0)
end_variable
begin_variable
var3
-1
2
Atom currstate(s_1_1)
NegatedAtom currstate(s_1_1)
end_variable
begin_variable
var4
-1
2
Atom currstate(t0)
NegatedAtom currstate(t0)
end_variable
begin_variable
var5
-1
2
Atom currstate(t1)
NegatedAtom currstate(t1)
end_variable
begin_variable
var6
-1
2
Atom currstate(t2)
NegatedAtom currstate(t2)
end_variable
0
begin_state
1
0
0
1
0
1
1
end_state
begin_goal
3
0 0
2 0
6 0
end_goal
9
begin_operator
add-a-ct0 
1
2 1
2
0 0 -1 0
0 1 0 1
1
end_operator
begin_operator
add-a-ct1 
1
1 1
2
0 2 0 1
0 3 -1 0
1
end_operator
begin_operator
add-a-ct2 
0
4
0 0 -1 0
0 1 0 1
0 2 0 1
0 3 -1 0
1
end_operator
begin_operator
add-c-ct3 
0
2
0 2 -1 0
0 3 0 1
1
end_operator
begin_operator
del-b-t0t1 
0
2
0 4 0 1
0 5 -1 0
1
end_operator
begin_operator
del-c-t1t2 
0
2
0 5 0 1
0 6 -1 0
1
end_operator
begin_operator
sync-b-t0t1 
0
2
0 4 0 1
0 5 -1 0
0
end_operator
begin_operator
sync-c-ct3 
0
4
0 2 -1 0
0 3 0 1
0 5 0 1
0 6 -1 0
0
end_operator
begin_operator
sync-c-t1t2 
1
3 1
2
0 5 0 1
0 6 -1 0
0
end_operator
0
