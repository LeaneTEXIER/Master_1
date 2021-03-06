#!/bin/bash -f
# ****************************************************************************
# Vivado (TM) v2018.2.1 (64-bit)
#
# Filename    : elaborate.sh
# Simulator   : Xilinx Vivado Simulator
# Description : Script for elaborating the compiled design
#
# Generated by Vivado on Wed Oct 03 08:43:56 CEST 2018
# SW Build 2288692 on Thu Jul 26 18:23:50 MDT 2018
#
# Copyright 1986-2018 Xilinx, Inc. All Rights Reserved.
#
# usage: elaborate.sh
#
# ****************************************************************************
ExecStep()
{
"$@"
RETVAL=$?
if [ $RETVAL -ne 0 ]
then
exit $RETVAL
fi
}
ExecStep xelab -wto a2d0c841b6ce4b8aa8be0e70972b2fe5 --incr --debug typical --relax --mt 8 -L xil_defaultlib -L secureip --snapshot tp3_exo1_behav xil_defaultlib.tp3_exo1 -log elaborate.log
