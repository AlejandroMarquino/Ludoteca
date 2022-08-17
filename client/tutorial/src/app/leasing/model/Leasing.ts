import { Customer } from "src/app/customer/model/Customer";
import { Game } from "src/app/game/model/Game";

export class Leasing {
    id: number;
    game: Game;
    customer: Customer;
    leasing_date: Date;
    end_date: Date;
}