package net.perfectdreams.loritta.commands.administration

import com.mrpowergamerbr.loritta.Loritta
import com.mrpowergamerbr.loritta.commands.*
import com.mrpowergamerbr.loritta.utils.locale.BaseLocale
import com.mrpowergamerbr.loritta.utils.LoriReply
import net.dv8tion.jda.core.Permission
import com.mrpowergamerbr.loritta.utils.LorittaPermission
// importar a nova framework e blabla
import net.perfectdreams.commands.annotation.Subcommand
import net.perfectdreams.commands.loritta.LorittaCommand
import net.perfectdreams.commands.loritta.LorittaCommandContext

class DashboardCommand : LorittaCommand(arrayOf("dashboard", "painel", "configurar", "config"), CommandCategory.ADMIN) {
    override fun getDescription(locale: BaseLocale): String? {
        return locale["commands.moderation.dashboard.description"]
    }

    override val canUseInPrivateChannel: Boolean = true

    @Subcommand
    suspend fun root(context: LorittaCommandContext, locale: BaseLocale) {

        val guild: String = context.guild.id.toString()
        val dashboard = "${Loritta.config.websiteUrl}dashboard"
        val url = "${dashboard}/configure/{$guild}"

        /*
        Se o comando for executado em guildas,
        e o autor tem permissão de alterar configurações no Dashboard (ou tem permissão de Gerenciar servidor),
        dê o url do dashboard diretamente pro servidor.
        */

        if (!context.isPrivateChannel && (context.lorittaUser.hasPermission(LorittaPermission.ALLOW_ACCESS_TO_DASHBOARD)) || context.guild.selfMember.hasPermission(Permission.MANAGE_SERVER)) {
            context.reply(
                    LoriReply(
                            "Dashboard: {$url}",
                            "<:wumplus:388417805126467594>"
                    )
            )
        } else {
            // Se o comando for executando em mensagem privada dê o negoco pra slecionar o servidor
            context.reply(
                    LoriReply(
                            "Dashboard: {$dashboard}",
                            "<:wumplus:388417805126467594>"
                    )
            )

        }

    }

}
