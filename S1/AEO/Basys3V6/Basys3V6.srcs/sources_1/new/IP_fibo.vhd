library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.std_logic_unsigned.all;

entity IP_fibo is
generic ( mycode : STD_LOGIC_VECTOR (10 downto 0):= "10000000011");
   port ( 
   clk : in  STD_LOGIC;
   reset : in  STD_LOGIC:='0';
   Tin : in  STD_LOGIC_VECTOR (31 downto 0);
   Tout : out  STD_LOGIC_VECTOR (31 downto 0);
   Ipcode : in  STD_LOGIC_VECTOR (10 downto 0); 
   IPdone : out STD_LOGIC);
end IP_fibo;
architecture dummyfibo of IP_fibo is
signal compteur : std_logic_vector(7 downto 0):=x"00";
signal compteur_i : std_logic_vector(7 downto 0):=x"00";

type state_type is (st1_idle, st2_nextfibo);
signal state, next_state : state_type;
signal fibo_gen_res : STD_LOGIC_VECTOR (31 downto 0);
signal IPdone_i : std_logic:='0';
signal init_i : std_logic := '1';
signal tin_i: STD_LOGIC_VECTOR (31 downto 0);

   
component fibogen is
   Port ( clk      : in  STD_LOGIC;
          init     : in  STD_LOGIC;
          fiboout  : out STD_LOGIC_VECTOR (31 downto 0));
end component;

begin

mfibogen : fibogen Port map ( 
           clk => clk,
           init => init_i,
           fiboout => fibo_gen_res
           );

   SYNC_PROC: process (clk)
   begin
      if (clk'event and clk = '1') then
         if (reset = '1') then
            state <= st1_idle;
            IPdone <= '0';            
            if(Ipcode = mycode) then
                Tout <= fibo_gen_res;
            else
                Tout <= (others => 'Z');
            end if;
         else
            IPdone <= IPdone_i;
            state <= next_state;                     
            compteur <= compteur_i;          
                 
         end if;
      end if;
   end process;

   OUTPUT_DECODE: process (state, compteur)
   begin
    case (state) is
        when st1_idle =>
            compteur_i <= Tin(7 downto 0);
            init_i <= '1';
            IPdone_i <= '0';
        when st2_nextfibo=>
            init_i <= '0';            
            if (compteur = x"02") then
                IPdone_i <= '1';
             else
                IPdone_i <= '0';
             end if;
             compteur_i <= compteur - '1';
         when others =>         
            init_i <= '1';
            IPdone_i <= '0';
     end case;
   end process;

   NEXT_STATE_DECODE: process (state, Ipcode, IPdone_i)
   begin
      next_state <= state;
      case (state) is
         when st1_idle =>            
            if (ipcode = Mycode) then
                next_state <= st2_nextfibo;
            end if;
         when st2_nextfibo =>
            if(IPdone_i = '1')then
                next_state <= st1_idle;
            end if;
         when others =>
            next_state <= state;
      end case;
   end process;
   
Tout <= fibo_gen_res when IPdone_i = '1' else (others => 'Z');

--    sel <= true when mycode = Ipcode else false;
--    IPdone <= '1' when sel else '0';
--    Tout <= x"12345678" when sel else ( others=>'Z');   
    
end dummyfibo;