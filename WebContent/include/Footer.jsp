<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<nav class="navbar navbar-default navbar-fixed-bottom navbar-inverse">
	<div class="container">
		<footer class="footer">
			<p class="pull-right" style="padding: 10px;">
				<a href="#backToTop" id="backToTop"> Back to top 
					<span class="glyphicon glyphicon-chevron-up"></span>
				</a>
			</p>
			<p class="text-muted" style="padding: 10px;">
				<span class="glyphicon glyphicon-copyright-mark"></span> Made by Ji
				Yuan
			</p>
		</footer>
	</div>
</nav>

<script type="text/javascript">
	$('#backToTop').click(function() {
		$('body').animate( {scrollTop : 0}, 500 );
		$('#backToTop').css({
			"text-decoration": "none"
		});
	})
</script>

</body>
</html>