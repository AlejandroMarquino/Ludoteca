import { Customer } from "src/app/customer/model/Customer";
import { Game } from "src/app/game/model/Game";

export class Leasing {
    id: number;
    game: Game;
    customer: Customer;
    leasingDate: Date;
    endDate: Date;
}