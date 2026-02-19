import { RoleEnum } from "../../generated/prisma/enums.js";
import { prisma } from "../../lib/prisma.js";

export async function createUserService(name: string, password: string, email: string, role: RoleEnum) {
    try {
        const user = await prisma.user.create({
            data: {
                name,
                email,
                password,
                role
            }
        });

        return {
            success: true,
            user
        };

    } catch (error) {
        return {
            success: false,
            user: null
        };
    }
}