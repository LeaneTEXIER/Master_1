----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 12/19/2018 10:09:14 AM
-- Design Name: 
-- Module Name: div - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

use IEEE.std_logic_unsigned.all;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity div is
    GENERIC (Mycode : std_logic_vector(10 downto 0):="00000010000" );
    Port ( IPcode : in  STD_LOGIC_vector(10 downto 0);
           clk : IN STD_LOGIC;
           reset : IN STD_LOGIC:='0';
           Tin : in STD_LOGIC_VECTOR(31 downto 0);
           Nin : in STD_LOGIC_VECTOR(31 downto 0);
           Tout : out STD_LOGIC_VECTOR(31 downto 0):=(others => 'Z');
           IPdone : out STD_LOGIC:='0');
end div;

architecture Behavioral of div is

type state_type is (idle, next_sub);
signal state, next_state : state_type;
signal done : std_logic:='0';
signal diviseur, diviseur_i : STD_LOGIC_VECTOR(15 downto 0):=Tin(15 downto 0);
signal compteur, compteur_i : STD_LOGIC_VECTOR(15 downto 0):=x"0000";


begin

   SYNC_PROC: process (clk)
   begin
      if (clk'event and clk = '1') then
         if (reset = '1') then
            state <= idle;
            IPdone <= '0';            
            if(Ipcode = mycode) then
                Tout <= "0000000000000000" & compteur;
            else
                Tout <= (others => 'Z');
            end if;
         else
            IPdone <= done;
            state <= next_state;                     
            compteur <= compteur_i;   
            diviseur_i <= diviseur; 
         end if;
      end if;
   end process;

   OUTPUT_DECODE: process (state, compteur)
   begin
    case (state) is
        when idle =>
            diviseur_i <= Tin(15 downto 0);
            compteur_i <= x"0000";
            done <= '0';
        when next_sub=>
              if(diviseur_i < Nin) then
                    done <= '1';
              else
                    done <= '0';
                    diviseur_i <= diviseur - Nin;
                    compteur_i <= compteur + 1;
              end if;
--            init_i <= '0';            
--            if (compteur = x"02") then
--                done <= '1';
--             else
--                done <= '0';
--             end if;
--             compteur_i <= compteur - '1';
         when others =>        
     end case;
   end process;

   NEXT_STATE_DECODE: process (state, Ipcode, done)
   begin
      next_state <= state;
      case (state) is
         when idle =>            
            if (ipcode = Mycode) then
                next_state <= next_sub;
            end if;
         when next_sub =>
            if(done = '1')then
                next_state <= idle;
            end if;
         when others =>
            next_state <= state;
      end case;
   end process;

end Behavioral;
