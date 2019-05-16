// Copyright 1986-2018 Xilinx, Inc. All Rights Reserved.
// --------------------------------------------------------------------------------
// Tool Version: Vivado v.2018.2.1 (lin64) Build 2288692 Thu Jul 26 18:23:50 MDT 2018
// Date        : Wed Dec 12 10:41:11 2018
// Host        : a13p6 running 64-bit Ubuntu 18.04.1 LTS
// Command     : write_verilog -force -mode funcsim -rename_top decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix -prefix
//               decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_ multiply_sim_netlist.v
// Design      : multiply
// Purpose     : This verilog netlist is a functional simulation representation of the design and should not be modified
//               or synthesized. This netlist cannot be used for SDF annotated simulation.
// Device      : xc7a35tcpg236-1
// --------------------------------------------------------------------------------
`timescale 1 ps / 1 ps

(* CHECK_LICENSE_TYPE = "multiply,mult_gen_v12_0_14,{}" *) (* downgradeipidentifiedwarnings = "yes" *) (* x_core_info = "mult_gen_v12_0_14,Vivado 2018.1" *) 
(* NotValidForBitStream *)
module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix
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
  decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_mult_gen_v12_0_14 U0
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
(* C_VERBOSITY = "0" *) (* C_XDEVICEFAMILY = "artix7" *) (* downgradeipidentifiedwarnings = "yes" *) 
module decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_mult_gen_v12_0_14
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
  decalper_eb_ot_sdeen_pot_pi_dehcac_xnilix_mult_gen_v12_0_14_viv i_mult
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
ICtaXfCzbOPH6sxL7IOdy7H9f+HYB7lt7Ox2sAfoDJZOMcrLZuFmmyYFB3b2Da1r52lZHwWqbvoh
frYx1CYdVXvWy9Df8zGwAcc2rhmCQ6uElzN7bUNpll9WXzhCSqCIIcbKIRjvorWm7ccbPQmyML3F
wlK9LaAfaPvpimMHw7MvOQhxvrOW++wJEFG1TQOUjfKEU/rX0deAGAD0APH4oPUCoJZ/upqhpS5t
PHuKPQPONTr/NrM20i6aO9qWtCBnZl+azYLS7R/Ea6fo9tsC2+/D/MvMEIqQ5islX3zHP6kT5Blg
87gXGyTmgD3yXWbxBMUztjKBGHTq4sEAlRIHkQ==

`pragma protect key_keyowner="Synplicity", key_keyname="SYNP15_1", key_method="rsa"
`pragma protect encoding = (enctype="BASE64", line_length=76, bytes=256)
`pragma protect key_block
qU8w9ljWz2WEe/C7aAFkF2xlDzVB3/fxehXbt+VFKWHH+bA2pW/alFrK+EpfqFpnC3Dnb4pARP8O
YtOuQ08vXxopOb7oxZM5rxW5UcV2IFsDpwS2j76my3fwl5xWXDrq1NOuFEM5k1Rj00zFglQra5yt
3SFE2RgcGQl931KQvAwBL7kqRrU/1UvjGAIvHa2ARClte5j7VnegSPyffdqIqa+1YEm8XMFtFph+
jMM3F5oTlPpZsjrAfDm6zNUXIHMHJecXGqaPbO6vHJTlPva7/Rx2aaiiknspYbBC/rpwBlmenpNb
mMsgzHKCbZFrsmjAJKxTbaxP6CHF0UFIOpjc3Q==

`pragma protect data_method = "AES128-CBC"
`pragma protect encoding = (enctype = "BASE64", line_length = 76, bytes = 6608)
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
