// Copyright 1986-2018 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2018.2.1 (lin64) Build 2288692 Thu Jul 26 18:23:50 MDT 2018
// Date        : Wed Dec 12 10:41:12 2018
// Host        : a13p6 running 64-bit Ubuntu 18.04.1 LTS
// Command     : write_verilog -force -mode funcsim
//               /home/m1/texierl/Documents/AEO/Homade1plus2/Homade1plus2.runs/multiply_synth_1/multiply_sim_netlist.v
// Design      : multiply
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a35tcpg236-1
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

(* CHECK_LICENSE_TYPE = "multiply,mult_gen_v12_0_14,{}" *) (* downgradeipidentifiedwarnings = "yes" *) (* x_core_info = "mult_gen_v12_0_14,Vivado 2018.1" *) 
(* NotValidForBitStream *)
module multiply
   (A,
    B,
    P);
  (* x_interface_info = "xilinx.com:signal:data:1.0 a_intf DATA" *) (* x_interface_parameter = "XIL_INTERFACENAME a_intf, LAYERED_METADATA undef" *) input [15:0]A;
  (* x_interface_info = "xilinx.com:signal:data:1.0 b_intf DATA" *) (* x_interface_parameter = "XIL_INTERFACENAME b_intf, LAYERED_METADATA undef" *) input [15:0]B;
  (* x_interface_info = "xilinx.com:signal:data:1.0 p_intf DATA" *) (* x_interface_parameter = "XIL_INTERFACENAME p_intf, LAYERED_METADATA undef" *) output [31:0]P;

  wire [15:0]A;
  wire [15:0]B;
  wire [31:0]P;
  wire [47:0]NLW_U0_PCASC_UNCONNECTED;
  wire [1:0]NLW_U0_ZERO_DETECT_UNCONNECTED;

  (* C_A_TYPE = "1" *) 
  (* C_A_WIDTH = "16" *) 
  (* C_B_TYPE = "1" *) 
  (* C_B_VALUE = "10000001" *) 
  (* C_B_WIDTH = "16" *) 
  (* C_CCM_IMP = "0" *) 
  (* C_CE_OVERRIDES_SCLR = "0" *) 
  (* C_HAS_CE = "0" *) 
  (* C_HAS_SCLR = "0" *) 
  (* C_HAS_ZERO_DETECT = "0" *) 
  (* C_LATENCY = "0" *) 
  (* C_MODEL_TYPE = "0" *) 
  (* C_MULT_TYPE = "1" *) 
  (* C_OPTIMIZE_GOAL = "1" *) 
  (* C_OUT_HIGH = "31" *) 
  (* C_OUT_LOW = "0" *) 
  (* C_ROUND_OUTPUT = "0" *) 
  (* C_ROUND_PT = "0" *) 
  (* C_VERBOSITY = "0" *) 
  (* C_XDEVICEFAMILY = "artix7" *) 
  (* downgradeipidentifiedwarnings = "yes" *) 
  multiply_mult_gen_v12_0_14 U0
       (.A(A),
        .B(B),
        .CE(1'b1),
        .CLK(1'b1),
        .P(P),
        .PCASC(NLW_U0_PCASC_UNCONNECTED[47:0]),
        .SCLR(1'b0),
        .ZERO_DETECT(NLW_U0_ZERO_DETECT_UNCONNECTED[1:0]));
endmodule

(* C_A_TYPE = "1" *) (* C_A_WIDTH = "16" *) (* C_B_TYPE = "1" *) 
(* C_B_VALUE = "10000001" *) (* C_B_WIDTH = "16" *) (* C_CCM_IMP = "0" *) 
(* C_CE_OVERRIDES_SCLR = "0" *) (* C_HAS_CE = "0" *) (* C_HAS_SCLR = "0" *) 
(* C_HAS_ZERO_DETECT = "0" *) (* C_LATENCY = "0" *) (* C_MODEL_TYPE = "0" *) 
(* C_MULT_TYPE = "1" *) (* C_OPTIMIZE_GOAL = "1" *) (* C_OUT_HIGH = "31" *) 
(* C_OUT_LOW = "0" *) (* C_ROUND_OUTPUT = "0" *) (* C_ROUND_PT = "0" *) 
(* C_VERBOSITY = "0" *) (* C_XDEVICEFAMILY = "artix7" *) (* ORIG_REF_NAME = "mult_gen_v12_0_14" *) 
(* downgradeipidentifiedwarnings = "yes" *) 
module multiply_mult_gen_v12_0_14
   (CLK,
    A,
    B,
    CE,
    SCLR,
    ZERO_DETECT,
    P,
    PCASC);
  input CLK;
  input [15:0]A;
  input [15:0]B;
  input CE;
  input SCLR;
  output [1:0]ZERO_DETECT;
  output [31:0]P;
  output [47:0]PCASC;

  wire \<const0> ;
  wire [15:0]A;
  wire [15:0]B;
  wire [31:0]P;
  wire [47:0]NLW_i_mult_PCASC_UNCONNECTED;
  wire [1:0]NLW_i_mult_ZERO_DETECT_UNCONNECTED;

  assign PCASC[47] = \<const0> ;
  assign PCASC[46] = \<const0> ;
  assign PCASC[45] = \<const0> ;
  assign PCASC[44] = \<const0> ;
  assign PCASC[43] = \<const0> ;
  assign PCASC[42] = \<const0> ;
  assign PCASC[41] = \<const0> ;
  assign PCASC[40] = \<const0> ;
  assign PCASC[39] = \<const0> ;
  assign PCASC[38] = \<const0> ;
  assign PCASC[37] = \<const0> ;
  assign PCASC[36] = \<const0> ;
  assign PCASC[35] = \<const0> ;
  assign PCASC[34] = \<const0> ;
  assign PCASC[33] = \<const0> ;
  assign PCASC[32] = \<const0> ;
  assign PCASC[31] = \<const0> ;
  assign PCASC[30] = \<const0> ;
  assign PCASC[29] = \<const0> ;
  assign PCASC[28] = \<const0> ;
  assign PCASC[27] = \<const0> ;
  assign PCASC[26] = \<const0> ;
  assign PCASC[25] = \<const0> ;
  assign PCASC[24] = \<const0> ;
  assign PCASC[23] = \<const0> ;
  assign PCASC[22] = \<const0> ;
  assign PCASC[21] = \<const0> ;
  assign PCASC[20] = \<const0> ;
  assign PCASC[19] = \<const0> ;
  assign PCASC[18] = \<const0> ;
  assign PCASC[17] = \<const0> ;
  assign PCASC[16] = \<const0> ;
  assign PCASC[15] = \<const0> ;
  assign PCASC[14] = \<const0> ;
  assign PCASC[13] = \<const0> ;
  assign PCASC[12] = \<const0> ;
  assign PCASC[11] = \<const0> ;
  assign PCASC[10] = \<const0> ;
  assign PCASC[9] = \<const0> ;
  assign PCASC[8] = \<const0> ;
  assign PCASC[7] = \<const0> ;
  assign PCASC[6] = \<const0> ;
  assign PCASC[5] = \<const0> ;
  assign PCASC[4] = \<const0> ;
  assign PCASC[3] = \<const0> ;
  assign PCASC[2] = \<const0> ;
  assign PCASC[1] = \<const0> ;
  assign PCASC[0] = \<const0> ;
  assign ZERO_DETECT[1] = \<const0> ;
  assign ZERO_DETECT[0] = \<const0> ;
  GND GND
       (.G(\<const0> ));
  (* C_A_TYPE = "1" *) 
  (* C_A_WIDTH = "16" *) 
  (* C_B_TYPE = "1" *) 
  (* C_B_VALUE = "10000001" *) 
  (* C_B_WIDTH = "16" *) 
  (* C_CCM_IMP = "0" *) 
  (* C_CE_OVERRIDES_SCLR = "0" *) 
  (* C_HAS_CE = "0" *) 
  (* C_HAS_SCLR = "0" *) 
  (* C_HAS_ZERO_DETECT = "0" *) 
  (* C_LATENCY = "0" *) 
  (* C_MODEL_TYPE = "0" *) 
  (* C_MULT_TYPE = "1" *) 
  (* C_OPTIMIZE_GOAL = "1" *) 
  (* C_OUT_HIGH = "31" *) 
  (* C_OUT_LOW = "0" *) 
  (* C_ROUND_OUTPUT = "0" *) 
  (* C_ROUND_PT = "0" *) 
  (* C_VERBOSITY = "0" *) 
  (* C_XDEVICEFAMILY = "artix7" *) 
  (* downgradeipidentifiedwarnings = "yes" *) 
  multiply_mult_gen_v12_0_14_viv i_mult
       (.A(A),
        .B(B),
        .CE(1'b0),
        .CLK(1'b0),
        .P(P),
        .PCASC(NLW_i_mult_PCASC_UNCONNECTED[47:0]),
        .SCLR(1'b0),
        .ZERO_DETECT(NLW_i_mult_ZERO_DETECT_UNCONNECTED[1:0]));
endmodule
`pragma protect begin_protected
`pragma protect version = 1
`pragma protect encrypt_agent = "XILINX"
`pragma protect encrypt_agent_info = "Xilinx Encryption Tool 2015"
`pragma protect key_keyowner="Cadence Design Systems.", key_keyname="cds_rsa_key", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=64)
`pragma protect key_block
TvNAk+dzefmJC5/xfGEoXo1v1zzw15yvf2w3I+7pl9weHnOYLTwk2CtA6qQwUdiv+KPlR09XyHxt
UocEiAlS9g==

`pragma protect key_keyowner="Synopsys", key_keyname="SNPS-VCS-RSA-2", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=128)
`pragma protect key_block
ccd1Kr3IgmbU3Zd5R5UGhugxe9OUvTTk5M/+YDzRXyTvXIMaUxHB5fv7SuuebIYqGrGlL5seA2Sg
zO1i2uQFXVFn4M1DHS2E7BwirWBP5gmU/RaWKyEfTu3E5ZGbc1lvK67CCG8szRwdrvmY+Z8CpiC4
+fKoXg6GREReZgylTmE=

`pragma protect key_keyowner="Aldec", key_keyname="ALDEC15_001", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=256)
`pragma protect key_block
D4OySXRBGdK3bWTwoBJnna9JJTCfjtow8OCB97TMc0CHJtgWscKG0sA6JP+WmQu+g/St8V3dnWCm
Z/oL2u8esW79WhsyQGAkuc2zUGutMTiH5JtlsxfFXreCjsbpfiQ4cOTSVV8RKFLaZCW+eXj7qQwk
WUd+Rk2Kp6kViZmb9GfGDSBc1qKbMuYuGLGiO+UVYNdt7dkYg9aAhJYx3c/Tx4m6BAZTpzEs9xzl
Mg0Plk7PRG/v5PXojT+9MvJ80iSqd3ejpG6kEE1mYBAhD1zmHQfbte6ipINFibjTuluuS5i0pIbf
HaA/nmULSj1xFBTfeEdDhm4CrFUWEdYvrJoOhg==

`pragma protect key_keyowner="ATRENTA", key_keyname="ATR-SG-2015-RSA-3", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=256)
`pragma protect key_block
YmbWYAZhC3ayB3FdtHMbSkvV5OWWIi6gmohNfeiL3hZEqSlPd2B43zehv3FM2BA2v3N0HlGO0TL6
neUbRccVG37R0aVoXEjetzHP+ZMpVpr2wNRYoVv9EAzvD7YjPAyiMQMLJO1wmw/LJVkGpP4UCg4g
tgMS7M+LmVgeot1Fmcwa4mDyquYpShDC0ZhYtWL3VmO204ubc1HcI1fEQiMp+tBP7rYU0jIyGMtz
dXGUYS7PdIYkz5ApCjSfCCueqmWeZf9/KXMkoo9udSh2ZyT9uNr+GM8fH8rcz5nZjN4ShPghIUSN
XIZbR6KJ/+WqugC6B6ULpEZUxft3AS1vxij4dA==

`pragma protect key_keyowner="Xilinx", key_keyname="xilinxt_2017_05", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=256)
`pragma protect key_block
pRgO0aX5waanQk0eZ4W7Q+LVxiXC+tf9hFRN9nsdM6xbA9apyUI0wd0pRjkzt/X5yvazLViQDSfS
Bm9cP+mYh23I891gOC2bMeto93RQUYlDhWmKA2HAuokJj6wKo/vk9LA0e/rAjHMWD7cTXHkdXPdz
d92x8sSRX6Z5gz0YOJ8hU+X3aLkMrr/d+Rs3UcELF+MTGSf53SzTuIbnaw08EsHUObyFusQxXlt6
ZuByaRiPP1ofEvMk+UCLRZThOA7sR6SIfjXOTF55TQgss4/Mf30sm+t84LW+xNBWIqVfiQ671PZF
CQ8K4qBj3nTT9D0FTUvfHdTmLtywWgV65+5W3A==

`pragma protect key_keyowner="Mentor Graphics Corporation", key_keyname="MGC-VELOCE-RSA", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=128)
`pragma protect key_block
d38DScsESf/yIfST5KEEwSUvjI+Km/dbua2xenGdzq3rgc/diAWKNIN11lcJIPDVBe6fB9J2TqbT
eXC+WnYP2YB9QXYlwKxLW7HOYcLC6Ivx9uoTg503B1azg5yB52W8iAwxelCieuRZ3qo4CxwOJ4w3
kwV+F675PsE0hWvEwTA=

`pragma protect key_keyowner="Mentor Graphics Corporation", key_keyname="MGC-VERIF-SIM-RSA-2", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=256)
`pragma protect key_block
Bf4H+OH1vHHXYQ0B+xvr52Pkbk3t9R17gzpbDdSPXjerF+p1mOwTJrxL3jQRkm9rUtVIgJGiq2/s
crniU3gwf/UiAzOrNxcIp9eKlLwDNsxSMYn+mkUQWlDdifqNNVK+YFJD0ZFE6pzyWAfSd99uwvf2
B/+VXkZFAWz3devN4zOqXGE5+OZKTJNNH2fm+gcI0n7V4lPByrga5xMdlx99MQZZRprmMts+yOHQ
eVL2q0jneXaC7j4j8aSjRtpPAjf6aWk9xkdj2iVGAqs6TlpdNPyA9bKumNf3XCjAnjbNwxHWWAao
tHbBrxiXF1qQUoAzJ9mjy31tCjRX+JQOzKafLw==

`pragma protect key_keyowner="Mentor Graphics Corporation", key_keyname="MGC-PREC-RSA", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=256)
`pragma protect key_block
gGsZZVILV/f738fNbpDMl0GwbHEPX7QWQx2qcoSt4nQA6PCiPsnJAeztFkAtIIDoIRFl7yZPu1sz
QR3nHVH4ZuE8cfd/cjTKPn4PLfL6wXI59qcUzZ6kNaykhMZar/Un4/DX3nWXGu5tnxte2rLAfvCL
6WMyHESg/Btvq83mqtbDQ5j9f8OGg9JcgIOnMEmEDpkT5Vi/qrptwwQuGEdcqfS1mOEZy1d4sHzT
UtE/FB6hejv1EkfNUorbd4ZBGn2PMAiu+0L4ExxGGZmMGtPRofS+8XohSNi4QpWQfBMMvujmRzVv
R2gR7zlN2D9dVNrsiwCopOLWrVxyofctHXXOhg==

`pragma protect key_keyowner="Synplicity", key_keyname="SYNP15_1", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=256)
`pragma protect key_block
6kozODgVITJkxZQ6hSE72jmIrluXdXFIY8U8STdZxhm2GTj9xV3WGs2yA+BQvtVRryBw/fv/NoKi
ZhD6mUJ/E8co2f0GrTql2+Gz4OnFHhmVKbpWlowLfHI+7X+99VC5fR0OEhOss5dpjWMjuN3zB+te
v2px7p/FVF52FeO+kQ8uLiFEQL6PjuZR79dolKLsZE6E2R6Fn2ppx+bZxUSdU7VqZGpT+4g4Lfok
/VAm1HB6I6nWSIqzk3xe8H1mcD1QJK26DPGWkNGFacFDKjP+49XbjmqbKSU5Tamba7dNzQ5ATz2i
Jisn899beIi1I8Z81vBAjWvDamKl1Hqvx+mjhQ==

`pragma protect data_method = "AES128-CBC"
`pragma protect encoding = (enctype = "BASE64", line_length = 76, bytes = 6592)
`pragma protect data_block
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
`pragma protect end_protected
`ifndef GLBL
`define GLBL
`timescale  1 ps / 1 ps

module glbl ();

    parameter ROC_WIDTH = 100000;
    parameter TOC_WIDTH = 0;

//--------   STARTUP Globals --------------
    wire GSR;
    wire GTS;
    wire GWE;
    wire PRLD;
    tri1 p_up_tmp;
    tri (weak1, strong0) PLL_LOCKG = p_up_tmp;

    wire PROGB_GLBL;
    wire CCLKO_GLBL;
    wire FCSBO_GLBL;
    wire [3:0] DO_GLBL;
    wire [3:0] DI_GLBL;
   
    reg GSR_int;
    reg GTS_int;
    reg PRLD_int;

//--------   JTAG Globals --------------
    wire JTAG_TDO_GLBL;
    wire JTAG_TCK_GLBL;
    wire JTAG_TDI_GLBL;
    wire JTAG_TMS_GLBL;
    wire JTAG_TRST_GLBL;

    reg JTAG_CAPTURE_GLBL;
    reg JTAG_RESET_GLBL;
    reg JTAG_SHIFT_GLBL;
    reg JTAG_UPDATE_GLBL;
    reg JTAG_RUNTEST_GLBL;

    reg JTAG_SEL1_GLBL = 0;
    reg JTAG_SEL2_GLBL = 0 ;
    reg JTAG_SEL3_GLBL = 0;
    reg JTAG_SEL4_GLBL = 0;

    reg JTAG_USER_TDO1_GLBL = 1'bz;
    reg JTAG_USER_TDO2_GLBL = 1'bz;
    reg JTAG_USER_TDO3_GLBL = 1'bz;
    reg JTAG_USER_TDO4_GLBL = 1'bz;

    assign (strong1, weak0) GSR = GSR_int;
    assign (strong1, weak0) GTS = GTS_int;
    assign (weak1, weak0) PRLD = PRLD_int;

    initial begin
	GSR_int = 1'b1;
	PRLD_int = 1'b1;
	#(ROC_WIDTH)
	GSR_int = 1'b0;
	PRLD_int = 1'b0;
    end

    initial begin
	GTS_int = 1'b1;
	#(TOC_WIDTH)
	GTS_int = 1'b0;
    end

endmodule
`endif
