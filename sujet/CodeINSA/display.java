	public String displayMap()
	{
		String u = "";
		String t = "";
		String s = "";
		String res="";
		int p;
		res += ("M[");
		for(int i = 0; i < world.length; i++)
		{
			res+="[";
			for(int j = 0; j < world.length; j++)
			{
				Land l = world.getLand(new Coordonnee(i, j));
				//player display
				p = l.player;
				//type of land display
				switch (l.type)
				{
					case MONTAGNE:
						t = "M";
						break;
					case FORET:
						t = "F";
						break;
					case PLAINE:
						t = "P";
						break;
					case RIVIER:
						t = "R";
						break;
				}
				//type of unit
				if(l.unit != null)
				{
					switch(l.unit.type)
					{
						case ARCHER:
							u = "A";
							break;
						case PAYSAN:
							u = "P";
							break;
						case BALIST:
							u = "B";
							break;
						case ECLAIREUR:
							u = "E";
							break;
						case INGE:
							u = "I";
							break;
						case NAIN:
							u ="N";
							break;
						case PALA:
							u = "C";
							break;
						case SOLDAT:
							u = "S";
							break;
					}
				}
				else
				{
					u = "N";
				}
				//type of structure
				if(l.struct != null)
				{
					switch(l.struct.getType())
					{
						case VILLE:
							s = "V";
							break;
						case FORT:
							s = "F";
							break;
						case HOPITAL:
							s = "H";
						case PONT:
							s = "P";
							break;
						case ROUTE:
							s = "R";
							break;
					}
				}
				else
				{
					s = "N";
				}
				//make the string
				res+=("["+t+";"+u+";"+s+";"+p+"];");
			}
			res+=("];");
		}
		res+="];U:";
		for(int i = 0; i < this.nbPlayer; i++)
		{
			res+="P"+i+":[";
			for(int j =0; j < this.gamer[i].nbUnit; j++)
			{
				if(this.gamer[i].army[j].alive())
				{
					Unit a = this.gamer[i].army[j];
					res+=""+j+","+a.action+","+a.life+";";
				}
			}
			res+="];"+this.gamer[i].gold+";";
		}
		return res;
	}