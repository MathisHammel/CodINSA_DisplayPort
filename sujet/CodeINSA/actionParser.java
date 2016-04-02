public String makeAction(int player, String message)
	{
		String res = null;
		int ret = 0;
		String actions[] = message.split(",", 5);
		switch (actions[0]) {
		//attack
		case "A":
			if(actions.length >= 4)
			{
				ret = this.attack(Integer.parseInt(actions[1]), new Coordonnee(Integer.parseInt(actions[2]),Integer.parseInt(actions[3])));
			}
			break;
			//build
		case "B":
			if(actions.length >= 3)
			{
				Structure st = null;
				switch(actions[2])
				{
				
					case "F":
						st =Structure.FORT;
						break;
					case "R":
						st =Structure.ROUTE;
						break;
					case "P":
						st =Structure.PONT;
					case "H":
						st =Structure.HOPITAL;
						break;
				
				}
				if(st == null)
				{
					ret = -1;
				}
				else
				{
					ret = this.build(Integer.parseInt(actions[1]), st);
				}
			}

			break;
			//create
		case "C":
			if(actions.length >= 2 )
			{
				unitType type = unitType.PAYSAN;
				//PAYSAN,ARCHER,ECLAIREUR,INGE,NAIN,PALA,BALIST,SOLDAT
				switch(actions[1])
				{
					//paysan
					case "P":
						type = unitType.PAYSAN;
						break;
					///archer
					case "A":
						type = unitType.ARCHER;
						break;
					case "N":
						type = unitType.NAIN;
						break;
					///archer
					case "B":
						type = unitType.BALIST;
						break;
					case "I":
						type = unitType.INGE;
						break;
					///archer
					case "E":
						type = unitType.ECLAIREUR;
						break;
					case "C":
						type = unitType.PALA;
						break;
					///archer
					case "S":
						type = unitType.SOLDAT;
						break;
				}
				ret = this.createUnit(type);
			}
			break;
			//destroy
		case "D":
			if(actions.length >= 2)
			{
				this.destroy(Integer.parseInt(actions[1]));
			}
			break;
			//move
		case "M":
			if(actions.length >= 4)
			{
				ret = this.move(Integer.parseInt(actions[1]),  new Coordonnee(Integer.parseInt(actions[2]),Integer.parseInt(actions[3])));
			}
			break;
		default:
			break;
		}
		if(ret != -1)
		{
			res = "OK:P"+this.playerTurn+" "+this.displayMap();
		}
		else
		{
			res = "KO"+this.displayMap();
		}
		return res;
	}