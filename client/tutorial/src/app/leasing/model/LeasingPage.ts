import { Pageable } from "src/app/core/model/page/Pageable";
import { Leasing } from "./Leasing";

export class LeasingPage {
    content: Leasing[];
    pageable: Pageable;
    totalElements: number;
}