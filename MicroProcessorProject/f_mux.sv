module f_mux(input logic [7:0] in0, in1,
             input logic sel,
				 output logic [7:0] out);

	assign out = (sel)?in1:in0;
				 
endmodule
