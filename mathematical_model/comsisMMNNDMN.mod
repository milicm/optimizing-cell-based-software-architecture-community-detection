param N;		#total number of vertices
param L1;
param L2;
param L:=1/L1;
set V;			#set of vertices
set FC;			#set of functionalities
set F{FC} within V;	#set of vertices of l-th functionality
set E, within V cross V;	#set of edges
set C:=1..N;		#set of possible communities
param b{E};	#weights of edges
param d{V};		#weights of vertices
param e:=1;
param broj_grana;
var y{V,C}, binary;
var z{V,V,C}, binary;
var x{C}, binary;
var broj, integer;

maximize modu: 1/L*sum{k in C}(sum{(i,j) in E}(b[i,j]*z[i,j,k])- 1/(4*L)*sum{i in V,j in V}(d[i]*d[j]*z[i,j,k]));
s.t.
lin1 {k in C, l in FC, i in F[l],j in F[l]}: z[i,j,k]<=y[i,k];
lin2 {k in C, l in FC, i in F[l],j in F[l]}: z[i,j,k]<=y[j,k];
lin3 {k in C, l in FC, i in F[l],j in F[l]}: z[i,j,k]>=y[i,k]+y[j,k]-1;
zabrane {k in C, l in FC, i in F[l], p in FC, j in F[p]: l<p}: y[i,k]+y[j,k]<=1;
prip {i in V}: sum{k in C} y[i,k]=1;
pomocna {i in V, k in C}: y[i,k]<=x[k];
broj1 {k in C}: sum{i in V}y[i,k]>=e*x[k];
end;

