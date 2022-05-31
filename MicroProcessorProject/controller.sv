
module controller(input logic [7:0] opcode,
                  input logic NFLG, ZFLG, reset, clk,
						output logic LOAD_AC, LOAD_IRU, LOAD_IRL, LOAD_PC, INCR_PC, FETCH, STORE_MEM, 
						output logic [3:0] STATE);
						
	typedef enum logic [3:0] {Start, PrepU, FetchU, PrepL, FetchL, Exec, StoreMem, ReadMem, Jump} statetype;
	statetype state, nextstate;
	
	// state switching
	always_ff @( negedge clk, posedge reset )
		if (reset) state <= Start;
		else       state <= nextstate;
	
	// state change flow
	always_comb
		case (state)
			Start:  nextstate = PrepU;
			PrepU:  nextstate = FetchU;
			FetchU: case (opcode)
			        8'h00: nextstate = PrepU;
					  8'h04: nextstate = Exec;
					  default: nextstate = PrepL;
					  endcase
					  
			PrepL:  case (opcode)
			        8'h10: nextstate = Jump;
					  8'h11: begin
					         if(NFLG) nextstate = Jump;
					         else nextstate = PrepU;
								end
					  8'h12: begin
					         if(~NFLG) nextstate = Jump;
					         else nextstate = PrepU;
								end
					  8'h13: begin
					         if(ZFLG) nextstate = Jump;
					         else nextstate = PrepU;
								end
					  8'h14: begin
					         if(~ZFLG) nextstate = Jump;
					         else nextstate = PrepU;
								end		
					  default: nextstate = FetchL;
			        endcase
					  
			FetchL: case (opcode)
					  8'h03: nextstate = StoreMem;
						
			        8'h02: nextstate = Exec;
					  8'h06: nextstate = Exec;
					  8'h08: nextstate = Exec;
					  8'h0E: nextstate = Exec;
					  8'h0F: nextstate = Exec;
					  
					  default: nextstate = ReadMem;
					  endcase
			
		   ReadMem: nextstate = Exec;
		   Exec: nextstate = PrepU;
			StoreMem: nextstate = PrepU;
			Jump: nextstate = PrepU;
			
		endcase
		
	// control line assignments
	assign FETCH     = (state == PrepU || state == FetchU || state == PrepL || state == FetchL);
	assign LOAD_IRU  = (state == FetchU);
	assign LOAD_IRL  = (state == FetchL);
	assign INCR_PC   = (state == FetchU || state == FetchL);
	assign STATE     = (state);
	assign LOAD_AC   = (state == Start || state == Exec);
	assign STORE_MEM = (state == StoreMem);
	assign LOAD_PC   = (state == Jump);
						
endmodule

